package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class FloatToChar implements ByteInput {

	private final FloatInput source;
	private final float d_scale;
	private static final float MIN_VAL = -128;
	private static final float MAX_VAL = 127;

	public FloatToChar(FloatInput source, float d_scale) {
		this.source = source;
		this.d_scale = d_scale;
	}

	@Override
	public byte readByte() throws IOException {
		float cur = 0.0f;
		cur = source.readFloat() * d_scale;
		if (cur > MAX_VAL) {
			cur = MAX_VAL;
		} else if (cur < MIN_VAL) {
			cur = MIN_VAL;
		}
		return (byte) Math.rint(cur);
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
