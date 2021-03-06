package ru.r2cloud.jradio.astrocast;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class AstrocastTest {

	private Astrocast input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 5;
		WavFileSource source = new WavFileSource(AstrocastTest.class.getClassLoader().getResourceAsStream("astrocast.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 8, "0111010111111010110000011010001101011000110100000110010001110110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 255 * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new Astrocast(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("AstrocastBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
