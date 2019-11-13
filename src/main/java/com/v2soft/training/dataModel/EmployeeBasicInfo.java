package com.v2soft.training.dataModel;

import java.util.Date;

public class EmployeeBasicInfo {
	private String employeeId;
	private String dateOfBirth;
	private String firstName;
	private String lastName;
	private String middleName;
	private String passportNumber;
	private String ssn;
	
	public EmployeeBasicInfo() {
		
	}
	
	public EmployeeBasicInfo(String employeeId, String dateOfBirth, String firstName, String lastName, String middleName,
			String passportNumber, String ssn) {
		super();
		this.employeeId = employeeId;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.passportNumber = passportNumber;
		this.ssn = ssn;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
}