package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmOBC {

	private byte NODENO;                    // redundant node number
	private boolean RST_EN;                 // the watchdog application is enabled to reset the node
	private byte BOTSLT;                    // currently running internal software slot
	private boolean SYNPPS;                 // shall the node synchronize with the PPS signal
	private boolean DISUTC;                 // shall the node distribute the UTC time at the next PPS signal
	private boolean DULBSY;                 // Indicates the state of the UploadManagers Flash Controller
	private HistoryBufferType TMMTFHIST;    // indicates the history buffer used to fill up transfer frames with history telemetry
	private SatelliteMode SAT_MODE;         // the current mode of the satellite
	private HistoryBufferType TMMHSHIST;    // indicates the history buffer used to fill up the high speed downlink
	private HighSpeedState TMMHSSTAT;       // indicates the current state of highspeed telemetry transmissions

	public StdTmOBC(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		NODENO = (byte) (raw >> 7);
		RST_EN = ((raw >> 6) & 0x1) > 0;
		BOTSLT = (byte) ((raw >> 3) & 0x7);
		SYNPPS = ((raw >> 2) & 0x1) > 0;
		DISUTC = ((raw >> 1) & 0x1) > 0;
		DULBSY = (raw & 0x1) > 0;
		raw = dis.readUnsignedByte();
		TMMTFHIST = HistoryBufferType.valueOfCode(raw >> 6);
		SAT_MODE = SatelliteMode.valueOfCode((raw >> 4) & 0x3);
		TMMHSHIST = HistoryBufferType.valueOfCode((raw >> 2) & 0x3);
		TMMHSSTAT = HighSpeedState.valueOfCode(raw & 0x3);

		dis.skipBytes(11);
	}

	public byte getNODENO() {
		return NODENO;
	}

	public void setNODENO(byte nODENO) {
		NODENO = nODENO;
	}

	public boolean isRST_EN() {
		return RST_EN;
	}

	public void setRST_EN(boolean rST_EN) {
		RST_EN = rST_EN;
	}

	public byte getBOTSLT() {
		return BOTSLT;
	}

	public void setBOTSLT(byte bOTSLT) {
		BOTSLT = bOTSLT;
	}

	public boolean isSYNPPS() {
		return SYNPPS;
	}

	public void setSYNPPS(boolean sYNPPS) {
		SYNPPS = sYNPPS;
	}

	public boolean isDISUTC() {
		return DISUTC;
	}

	public void setDISUTC(boolean dISUTC) {
		DISUTC = dISUTC;
	}

	public boolean isDULBSY() {
		return DULBSY;
	}

	public void setDULBSY(boolean dULBSY) {
		DULBSY = dULBSY;
	}

	public HistoryBufferType getTMMTFHIST() {
		return TMMTFHIST;
	}

	public void setTMMTFHIST(HistoryBufferType tMMTFHIST) {
		TMMTFHIST = tMMTFHIST;
	}

	public SatelliteMode getSAT_MODE() {
		return SAT_MODE;
	}

	public void setSAT_MODE(SatelliteMode sAT_MODE) {
		SAT_MODE = sAT_MODE;
	}

	public HistoryBufferType getTMMHSHIST() {
		return TMMHSHIST;
	}

	public void setTMMHSHIST(HistoryBufferType tMMHSHIST) {
		TMMHSHIST = tMMHSHIST;
	}

	public HighSpeedState getTMMHSSTAT() {
		return TMMHSSTAT;
	}

	public void setTMMHSSTAT(HighSpeedState tMMHSSTAT) {
		TMMHSSTAT = tMMHSSTAT;
	}

}
