package ru.r2cloud.jradio;

import java.io.IOException;

public abstract class Beacon implements Externalizable {

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;
	
	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		readBeacon(data);
	}
	
	public abstract void readBeacon(byte[] data) throws IOException;

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}
	
	public long getBeginSample() {
		return beginSample;
	}
	
	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}
}
