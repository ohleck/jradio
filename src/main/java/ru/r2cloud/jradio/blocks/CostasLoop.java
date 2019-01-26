package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class CostasLoop implements FloatInput {

	private static final double ONE_AND_HALF_PI = 3 * Math.PI / 2;
	private static final double HALF_PI = Math.PI / 2;
	private final FloatInput source;
	private final ControlLoop controlLoop;

	private float error = 0.0f;
	private final int order;
	private final boolean useSnr;

	private boolean outputReal = true;
	private float img;

	public CostasLoop(FloatInput source, float loopBw, int order, boolean useSnr) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		controlLoop = new ControlLoop(loopBw, 1.0f, -1.0f);
		if (useSnr || (order != 4 && order != 2)) {
			throw new IllegalArgumentException("unsupported snr: " + useSnr + " and order: " + order);
		}
		this.order = order;
		this.useSnr = useSnr;
		this.source = source;
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			float origReal = source.readFloat();
			img = source.readFloat();

			float phaseToCalc = -controlLoop.getPhase();
			double sinImg = Math.sin(phaseToCalc);
			float cosReal = cos(phaseToCalc, sinImg);

			float real = origReal * cosReal - img * (float) sinImg;
			img = origReal * (float) sinImg + img * cosReal;

			switch (order) {
			case 4:
				if (!useSnr) {
					if (real > 0.0f) {
						error = 1.0f;
					} else {
						error = -1.0f;
					}
					error *= img;
					if (img > 0.0f) {
						error -= (1.0f) * real;
					} else {
						error -= (-1.0f) * real;
					}
				}
				break;
			case 2:
				if (!useSnr) {
					error = real * img;
				}
				break;
			default:
				throw new IllegalArgumentException("unsupported order: " + order);
			}
			error = MathUtils.branchless_clip(error, 1.0f);

			controlLoop.advanceLoop(error);

			outputReal = !outputReal;
			return real;
		}
		outputReal = !outputReal;
		return img;
	}

	private static float cos(float phaseToCalc, double sinImg) {
		float cosReal = (float) Math.sqrt(1 - sinImg * sinImg);
		if (phaseToCalc >= 0 && (phaseToCalc > HALF_PI) && sinImg >= 0.0) {
			cosReal = -cosReal;
		} else if (phaseToCalc >= 0 && sinImg < 0.0 && phaseToCalc < ONE_AND_HALF_PI) {
			cosReal = -cosReal;
		} else if (phaseToCalc < 0 && sinImg >= 0.0 && phaseToCalc > -ONE_AND_HALF_PI) {
			cosReal = -cosReal;
		} else if (phaseToCalc < 0 && sinImg < 0.0 && phaseToCalc < -HALF_PI) {
			cosReal = -cosReal;
		}
		return cosReal;
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
