package com.v2soft.training.service;

import java.util.List;

import com.v2soft.training.dataModel.EmployeeAddressMod;
import com.v2soft.training.dataModel.EmployeeBasicInfo;
import com.v2soft.training.dataModel.EmployeeMod;
import com.v2soft.training.dataModel.LoginCredentials;
import com.v2soft.training.dataModel.ValidateUser;

public interface EmployeeService {
	List<EmployeeMod> getEmployeeList();    
	
	EmployeeMod getEmployeeById(String employeeId);

	ValidateUser validateUser(LoginCredentials login);
	
	void addEmployee(EmployeeMod employee);

	void deleteEmployee(String employeeId);
}