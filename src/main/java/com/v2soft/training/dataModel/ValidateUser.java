package com.v2soft.training.dataModel;

import java.util.UUID;

public class ValidateUser {
	private EmployeeMod emp;
	private UUID uuid;
	
	public ValidateUser() {
		
	}
	
	public ValidateUser(EmployeeMod emp, UUID uuid) {
		super();
		this.emp = emp;
		this.uuid = uuid;
	}
	public EmployeeMod getEmp() {
		return emp;
	}
	public void setEmp(EmployeeMod emp) {
		this.emp = emp;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	
}