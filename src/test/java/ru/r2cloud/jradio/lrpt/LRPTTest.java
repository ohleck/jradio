package ru.r2cloud.jradio.lrpt;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.meteor.MeteorImage;
import ru.r2cloud.jradio.source.InputStreamSource;

public class LRPTTest {

	private LRPT lrpt;

	@Test
	public void success() throws Exception {
		PhaseAmbiguityResolver phaseAmbiguityResolver = new PhaseAmbiguityResolver(0x035d49c24ff2686bL);
		InputStreamSource float2char = new InputStreamSource(LRPTTest.class.getClassLoader().getResourceAsStream("8bitsoft.s"));
		CorrelateAccessCodeTag correlate = new CorrelateAccessCodeTag(float2char, 9, phaseAmbiguityResolver.getSynchronizationMarkers(), true);
		TaggedStreamToPdu tag = new TaggedStreamToPdu(new FixedLengthTagger(correlate, 8160 * 2 + 8 * 2));
		lrpt = new LRPT(tag, phaseAmbiguityResolver, MeteorImage.METEOR_SPACECRAFT_ID);
		assertTrue(lrpt.hasNext());
		AssertJson.assertObjectsEqual("VCDU.json", lrpt.next());
	}

	@After
	public void stop() throws IOException {
		if (lrpt != null) {
			lrpt.close();
		}
	}

}
