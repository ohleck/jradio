package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HkStatus {

	// HK request is active
	private boolean PMM;
	private boolean PMR;
	private boolean TMM;
	private boolean TMR;
	private boolean SSM;
	private boolean SSR;
	private boolean ESE;
	private boolean MWR;
	private boolean MWM;
	private boolean MPS;
	private boolean MMM;
	private boolean MMR;
	private boolean MTM;
	private boolean MTR;
	private boolean TRI;
	private boolean LMP;
	private boolean PCAM;
	private boolean AMS;
	private boolean STX;
	private boolean GPS;

	private boolean SCAM;
	private boolean automaticRotation;
	private boolean general;
	private boolean power;
	private boolean OBD;
	private boolean AOCS;
	private boolean FDIRTMTC;
	private boolean payload;
	private boolean disabled;

	public HkStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();

		PMM = ((raw >> 5) & 0x1) > 0;
		PMR = ((raw >> 4) & 0x1) > 0;
		TMM = ((raw >> 3) & 0x1) > 0;
		TMR = ((raw >> 2) & 0x1) > 0;
		SSM = ((raw >> 1) & 0x1) > 0;
		SSR = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ESE = ((raw >> 7) & 0x1) > 0;
		MWR = ((raw >> 6) & 0x1) > 0;
		MWM = ((raw >> 5) & 0x1) > 0;
		MPS = ((raw >> 4) & 0x1) > 0;
		MMM = ((raw >> 3) & 0x1) > 0;
		MMR = ((raw >> 2) & 0x1) > 0;
		MTM = ((raw >> 1) & 0x1) > 0;
		MTR = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRI = ((raw >> 7) & 0x1) > 0;
		LMP = ((raw >> 6) & 0x1) > 0;
		PCAM = ((raw >> 5) & 0x1) > 0;
		AMS = ((raw >> 4) & 0x1) > 0;
		STX = ((raw >> 3) & 0x1) > 0;
		GPS = ((raw >> 2) & 0x1) > 0;

		SCAM = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		automaticRotation = ((raw >> 7) & 0x1) > 0;
		general = ((raw >> 6) & 0x1) > 0;
		power = ((raw >> 5) & 0x1) > 0;
		OBD = ((raw >> 4) & 0x1) > 0;
		AOCS = ((raw >> 3) & 0x1) > 0;
		FDIRTMTC = ((raw >> 2) & 0x1) > 0;
		payload = ((raw >> 1) & 0x1) > 0;
		disabled = (raw & 0x1) > 0;
	}

	public boolean isPMM() {
		return PMM;
	}

	public void setPMM(boolean pMM) {
		PMM = pMM;
	}

	public boolean isPMR() {
		return PMR;
	}

	public void setPMR(boolean pMR) {
		PMR = pMR;
	}

	public boolean isTMM() {
		return TMM;
	}

	public void setTMM(boolean tMM) {
		TMM = tMM;
	}

	public boolean isTMR() {
		return TMR;
	}

	public void setTMR(boolean tMR) {
		TMR = tMR;
	}

	public boolean isSSM() {
		return SSM;
	}

	public void setSSM(boolean sSM) {
		SSM = sSM;
	}

	public boolean isSSR() {
		return SSR;
	}

	public void setSSR(boolean sSR) {
		SSR = sSR;
	}

	public boolean isESE() {
		return ESE;
	}

	public void setESE(boolean eSE) {
		ESE = eSE;
	}

	public boolean isMWR() {
		return MWR;
	}

	public void setMWR(boolean mWR) {
		MWR = mWR;
	}

	public boolean isMWM() {
		return MWM;
	}

	public void setMWM(boolean mWM) {
		MWM = mWM;
	}

	public boolean isMPS() {
		return MPS;
	}

	public void setMPS(boolean mPS) {
		MPS = mPS;
	}

	public boolean isMMM() {
		return MMM;
	}

	public void setMMM(boolean mMM) {
		MMM = mMM;
	}

	public boolean isMMR() {
		return MMR;
	}

	public void setMMR(boolean mMR) {
		MMR = mMR;
	}

	public boolean isMTM() {
		return MTM;
	}

	public void setMTM(boolean mTM) {
		MTM = mTM;
	}

	public boolean isMTR() {
		return MTR;
	}

	public void setMTR(boolean mTR) {
		MTR = mTR;
	}

	public boolean isTRI() {
		return TRI;
	}

	public void setTRI(boolean tRI) {
		TRI = tRI;
	}

	public boolean isLMP() {
		return LMP;
	}

	public void setLMP(boolean lMP) {
		LMP = lMP;
	}

	public boolean isPCAM() {
		return PCAM;
	}

	public void setPCAM(boolean pCAM) {
		PCAM = pCAM;
	}

	public boolean isAMS() {
		return AMS;
	}

	public void setAMS(boolean aMS) {
		AMS = aMS;
	}

	public boolean isSTX() {
		return STX;
	}

	public void setSTX(boolean sTX) {
		STX = sTX;
	}

	public boolean isGPS() {
		return GPS;
	}

	public void setGPS(boolean gPS) {
		GPS = gPS;
	}

	public boolean isSCAM() {
		return SCAM;
	}

	public void setSCAM(boolean sCAM) {
		SCAM = sCAM;
	}

	public boolean isAutomaticRotation() {
		return automaticRotation;
	}

	public void setAutomaticRotation(boolean automaticRotation) {
		this.automaticRotation = automaticRotation;
	}

	public boolean isGeneral() {
		return general;
	}

	public void setGeneral(boolean general) {
		this.general = general;
	}

	public boolean isPower() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	public boolean isOBD() {
		return OBD;
	}

	public void setOBD(boolean oBD) {
		OBD = oBD;
	}

	public boolean isAOCS() {
		return AOCS;
	}

	public void setAOCS(boolean aOCS) {
		AOCS = aOCS;
	}

	public boolean isFDIRTMTC() {
		return FDIRTMTC;
	}

	public void setFDIRTMTC(boolean fDIRTMTC) {
		FDIRTMTC = fDIRTMTC;
	}

	public boolean isPayload() {
		return payload;
	}

	public void setPayload(boolean payload) {
		this.payload = payload;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
