package com.v2soft.training.dataModel;
// Generated Oct 9, 2019, 1:39:32 PM by Hibernate Tools 5.1.10.Final

import java.util.HashSet;
import java.util.Set;

/**
 * AddressType generated by hbm2java
 */
public class AddressTypeMod implements java.io.Serializable {

	private int id;
	private String type;
	private Set<EmployeeAddressMod> employeeAddresses = new HashSet<EmployeeAddressMod>(0);

	public AddressTypeMod() {
	}

	public AddressTypeMod(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public AddressTypeMod(int id, String type, Set<EmployeeAddressMod> employeeAddresses) {
		this.id = id;
		this.type = type;
		this.employeeAddresses = employeeAddresses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<EmployeeAddressMod> getEmployeeAddresses() {
		return this.employeeAddresses;
	}

	public void setEmployeeAddresses(Set<EmployeeAddressMod> employeeAddresses) {
		this.employeeAddresses = employeeAddresses;
	}

}