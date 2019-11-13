package com.v2soft.training.dataModel;
// Generated Oct 9, 2019, 1:39:32 PM by Hibernate Tools 5.1.10.Final

public class EmployeeAddressIdMod implements java.io.Serializable {

	private String employeeId;
	private int addressTypeId;

	public EmployeeAddressIdMod() {
	}

	public EmployeeAddressIdMod(String employeeId, int addressTypeId) {
		this.employeeId = employeeId;
		this.addressTypeId = addressTypeId;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getAddressTypeId() {
		return this.addressTypeId;
	}

	public void setAddressTypeId(int addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EmployeeAddressIdMod))
			return false;
		EmployeeAddressIdMod castOther = (EmployeeAddressIdMod) other;

		return ((this.getEmployeeId() == castOther.getEmployeeId()) || (this.getEmployeeId() != null
				&& castOther.getEmployeeId() != null && this.getEmployeeId().equals(castOther.getEmployeeId())))
				&& (this.getAddressTypeId() == castOther.getAddressTypeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEmployeeId() == null ? 0 : this.getEmployeeId().hashCode());
		result = 37 * result + this.getAddressTypeId();
		return result;
	}

}

