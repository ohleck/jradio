package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CpuError {

	private boolean HardFaultVECTTBL;// forced hard fault generated by escalation
	private boolean HardFaultFORCED;// hard fault during exception processing
	private boolean MemManageIACCVIOL;// Instruction access violating flag
	private boolean MemManageDACCVIOL;// data access violating flag
	private boolean MemManageMSTKERR;// memory manager fault on unstacking for a return from exception
	private boolean MemManageMUNKSERR;// memory manage fault on stacking for exception entry
	private boolean MemManageMLSPERR;// fault occurred during floating point state preservation
	private boolean BusErrorSTKERR;// bus fault on stacking for exception entry
	private boolean BusErrorUNSTKERR;// bus fault on unstacking for a return on exception
	private boolean BusErrorIBUSERR;// instruction bus error
	private boolean BusErrorLSPERR;// nus fault on floating point lazy state preservation
	private boolean BusErrorPRECISERR;// precise data bus error
	private boolean BusErrorIMPRECISERR;// imprecise data bus error
	private boolean UsageFaultNOCP;// no coprocessor usage fault
	private boolean UsageFaultUNDEFINSTR;// undefined instruction usage fault
	private boolean UsageFaultINVSTATE;// Invalid state usage fault
	private boolean UsageFaultINVCP;// Invalid PC load usage fault
	private boolean UsageFaultUNALIGNED;// unaligned access usage fault
	private boolean UsageFaultDIVBYZERO;// division by zero fault

	public CpuError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		HardFaultVECTTBL = ((raw >> 7) & 0x1) > 0;
		HardFaultFORCED = ((raw >> 6) & 0x1) > 0;
		MemManageIACCVIOL = ((raw >> 5) & 0x1) > 0;
		MemManageDACCVIOL = ((raw >> 4) & 0x1) > 0;
		MemManageMSTKERR = ((raw >> 3) & 0x1) > 0;
		MemManageMUNKSERR = ((raw >> 2) & 0x1) > 0;
		MemManageMLSPERR = ((raw >> 1) & 0x1) > 0;
		BusErrorSTKERR = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		BusErrorUNSTKERR = ((raw >> 7) & 0x1) > 0;
		BusErrorIBUSERR = ((raw >> 6) & 0x1) > 0;
		BusErrorLSPERR = ((raw >> 5) & 0x1) > 0;
		BusErrorPRECISERR = ((raw >> 4) & 0x1) > 0;
		BusErrorIMPRECISERR = ((raw >> 3) & 0x1) > 0;
		UsageFaultNOCP = ((raw >> 2) & 0x1) > 0;
		UsageFaultUNDEFINSTR = ((raw >> 1) & 0x1) > 0;
		UsageFaultINVSTATE = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		UsageFaultINVCP = ((raw >> 7) & 0x1) > 0;
		UsageFaultUNALIGNED = ((raw >> 6) & 0x1) > 0;
		UsageFaultDIVBYZERO = ((raw >> 5) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isHardFaultVECTTBL() {
		return HardFaultVECTTBL;
	}

	public void setHardFaultVECTTBL(boolean hardFaultVECTTBL) {
		HardFaultVECTTBL = hardFaultVECTTBL;
	}

	public boolean isHardFaultFORCED() {
		return HardFaultFORCED;
	}

	public void setHardFaultFORCED(boolean hardFaultFORCED) {
		HardFaultFORCED = hardFaultFORCED;
	}

	public boolean isMemManageIACCVIOL() {
		return MemManageIACCVIOL;
	}

	public void setMemManageIACCVIOL(boolean memManageIACCVIOL) {
		MemManageIACCVIOL = memManageIACCVIOL;
	}

	public boolean isMemManageDACCVIOL() {
		return MemManageDACCVIOL;
	}

	public void setMemManageDACCVIOL(boolean memManageDACCVIOL) {
		MemManageDACCVIOL = memManageDACCVIOL;
	}

	public boolean isMemManageMSTKERR() {
		return MemManageMSTKERR;
	}

	public void setMemManageMSTKERR(boolean memManageMSTKERR) {
		MemManageMSTKERR = memManageMSTKERR;
	}

	public boolean isMemManageMUNKSERR() {
		return MemManageMUNKSERR;
	}

	public void setMemManageMUNKSERR(boolean memManageMUNKSERR) {
		MemManageMUNKSERR = memManageMUNKSERR;
	}

	public boolean isMemManageMLSPERR() {
		return MemManageMLSPERR;
	}

	public void setMemManageMLSPERR(boolean memManageMLSPERR) {
		MemManageMLSPERR = memManageMLSPERR;
	}

	public boolean isBusErrorSTKERR() {
		return BusErrorSTKERR;
	}

	public void setBusErrorSTKERR(boolean busErrorSTKERR) {
		BusErrorSTKERR = busErrorSTKERR;
	}

	public boolean isBusErrorUNSTKERR() {
		return BusErrorUNSTKERR;
	}

	public void setBusErrorUNSTKERR(boolean busErrorUNSTKERR) {
		BusErrorUNSTKERR = busErrorUNSTKERR;
	}

	public boolean isBusErrorIBUSERR() {
		return BusErrorIBUSERR;
	}

	public void setBusErrorIBUSERR(boolean busErrorIBUSERR) {
		BusErrorIBUSERR = busErrorIBUSERR;
	}

	public boolean isBusErrorLSPERR() {
		return BusErrorLSPERR;
	}

	public void setBusErrorLSPERR(boolean busErrorLSPERR) {
		BusErrorLSPERR = busErrorLSPERR;
	}

	public boolean isBusErrorPRECISERR() {
		return BusErrorPRECISERR;
	}

	public void setBusErrorPRECISERR(boolean busErrorPRECISERR) {
		BusErrorPRECISERR = busErrorPRECISERR;
	}

	public boolean isBusErrorIMPRECISERR() {
		return BusErrorIMPRECISERR;
	}

	public void setBusErrorIMPRECISERR(boolean busErrorIMPRECISERR) {
		BusErrorIMPRECISERR = busErrorIMPRECISERR;
	}

	public boolean isUsageFaultNOCP() {
		return UsageFaultNOCP;
	}

	public void setUsageFaultNOCP(boolean usageFaultNOCP) {
		UsageFaultNOCP = usageFaultNOCP;
	}

	public boolean isUsageFaultUNDEFINSTR() {
		return UsageFaultUNDEFINSTR;
	}

	public void setUsageFaultUNDEFINSTR(boolean usageFaultUNDEFINSTR) {
		UsageFaultUNDEFINSTR = usageFaultUNDEFINSTR;
	}

	public boolean isUsageFaultINVSTATE() {
		return UsageFaultINVSTATE;
	}

	public void setUsageFaultINVSTATE(boolean usageFaultINVSTATE) {
		UsageFaultINVSTATE = usageFaultINVSTATE;
	}

	public boolean isUsageFaultINVCP() {
		return UsageFaultINVCP;
	}

	public void setUsageFaultINVCP(boolean usageFaultINVCP) {
		UsageFaultINVCP = usageFaultINVCP;
	}

	public boolean isUsageFaultUNALIGNED() {
		return UsageFaultUNALIGNED;
	}

	public void setUsageFaultUNALIGNED(boolean usageFaultUNALIGNED) {
		UsageFaultUNALIGNED = usageFaultUNALIGNED;
	}

	public boolean isUsageFaultDIVBYZERO() {
		return UsageFaultDIVBYZERO;
	}

	public void setUsageFaultDIVBYZERO(boolean usageFaultDIVBYZERO) {
		UsageFaultDIVBYZERO = usageFaultDIVBYZERO;
	}

}
