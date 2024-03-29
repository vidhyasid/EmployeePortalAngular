package com.v2soft.training.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Login generated by hbm2java
 */
@Entity
@Table(name = "login", catalog = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class EmployeeLogin implements java.io.Serializable {

	private EmployeeLoginId id;
	private Employee employeeinfo;
	private String password;
	private Set<LoginSession> loginSessions = new HashSet<LoginSession>(0);

	public EmployeeLogin() {
	}

	public EmployeeLogin(EmployeeLoginId id, Employee employeeinfo, String password) {
		this.id = id;
		this.employeeinfo = employeeinfo;
		this.password = password;
	}

	public EmployeeLogin(EmployeeLoginId id, Employee employeeinfo, String password, Set<LoginSession> loginSessions) {
		this.id = id;
		this.employeeinfo = employeeinfo;
		this.password = password;
		this.loginSessions = loginSessions;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userName", column = @Column(name = "userName", unique = true, nullable = false, length = 20)),
			@AttributeOverride(name = "employeeId", column = @Column(name = "Employee_id", nullable = false, length = 12)) })
	public EmployeeLoginId getId() {
		return this.id;
	}

	public void setId(EmployeeLoginId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Employee_id", nullable = false, insertable = false, updatable = false)
	public Employee getEmployeeinfo() {
		return this.employeeinfo;
	}

	public void setEmployeeinfo(Employee employeeinfo) {
		this.employeeinfo = employeeinfo;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "login")
	public Set<LoginSession> getLoginSessions() {
		return this.loginSessions;
	}

	public void setLoginSessions(Set<LoginSession> loginSessions) {
		this.loginSessions = loginSessions;
	}

}
