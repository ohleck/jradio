package ru.r2cloud.jradio.suomi100;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.csp.Header;

public class Suomi100 extends BeaconSource<Suomi100Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Suomi100.class);
	private static final int CRC32_LENGTH = 4;

	public Suomi100(MessageInput input) {
		super(input);
	}

	@Override
	protected Suomi100Beacon parseBeacon(byte[] raw) {
		if (raw.length < (Header.LENGTH + 2 * CRC32_LENGTH)) {
			return null;
		}
		// validate frame crc32
		long actualCrc32 = ((raw[raw.length - 4] & 0xFFL) << 24) | ((raw[raw.length - 3] & 0xFFL) << 16) | ((raw[raw.length - 2] & 0xFFL) << 8) | (raw[raw.length - 1] & 0xFFL);
		long expectedCrc32 = Crc32c.calculate(raw, 0, raw.length - CRC32_LENGTH);
		if (actualCrc32 != expectedCrc32) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatched");
			}
			return null;
		}
		// validate data crc32
		actualCrc32 = ((raw[raw.length - 8] & 0xFFL) << 24) | ((raw[raw.length - 7] & 0xFFL) << 16) | ((raw[raw.length - 6] & 0xFFL) << 8) | (raw[raw.length - 5] & 0xFFL);
		expectedCrc32 = Crc32c.calculate(raw, Header.LENGTH, raw.length - (Header.LENGTH + 2 * CRC32_LENGTH));
		if (actualCrc32 != expectedCrc32) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatched");
			}
			return null;
		}
		Suomi100Beacon result = new Suomi100Beacon();
		try {
			result.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}

}
