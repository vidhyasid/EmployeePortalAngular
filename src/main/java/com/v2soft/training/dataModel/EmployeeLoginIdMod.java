package com.v2soft.training.dataModel;

/**
 * LoginId generated by hbm2java
 */
public class EmployeeLoginIdMod implements java.io.Serializable {

	private String userName;
	private String employeeId;

	public EmployeeLoginIdMod() {
	}

	public EmployeeLoginIdMod(String userName, String employeeId) {
		this.userName = userName;
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EmployeeLoginIdMod))
			return false;
		EmployeeLoginIdMod castOther = (EmployeeLoginIdMod) other;

		return ((this.getUserName() == castOther.getUserName()) || (this.getUserName() != null
				&& castOther.getUserName() != null && this.getUserName().equals(castOther.getUserName())))
				&& ((this.getEmployeeId() == castOther.getEmployeeId())
						|| (this.getEmployeeId() != null && castOther.getEmployeeId() != null
								&& this.getEmployeeId().equals(castOther.getEmployeeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result + (getEmployeeId() == null ? 0 : this.getEmployeeId().hashCode());
		return result;
	}

}
