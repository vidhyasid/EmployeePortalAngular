package com.v2soft.training.model;
// Generated Oct 9, 2019, 1:39:32 PM by Hibernate Tools 5.1.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AddressType generated by hbm2java
 */
@Entity
@Table(name = "address_type", catalog = "employee")
public class AddressType implements java.io.Serializable {

	private int id;
	private String type;
	private Set<EmployeeAddress> employeeAddresses = new HashSet<EmployeeAddress>(0);

	public AddressType() {
	}

	public AddressType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public AddressType(int id, String type, Set<EmployeeAddress> employeeAddresses) {
		this.id = id;
		this.type = type;
		this.employeeAddresses = employeeAddresses;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Type", nullable = false, length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressType")	
	public Set<EmployeeAddress> getEmployeeAddresses() {
		return this.employeeAddresses;
	}

	public void setEmployeeAddresses(Set<EmployeeAddress> employeeAddresses) {
		this.employeeAddresses = employeeAddresses;
	}

}
