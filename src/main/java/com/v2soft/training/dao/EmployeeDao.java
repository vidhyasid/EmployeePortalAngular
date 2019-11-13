package com.v2soft.training.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.v2soft.training.dataModel.EmployeeAddressMod;
import com.v2soft.training.dataModel.EmployeeBasicInfo;
import com.v2soft.training.dataModel.EmployeeMod;
import com.v2soft.training.dataModel.LoginCredentials;
import com.v2soft.training.dataModel.ValidateUser;

//@Component
public interface EmployeeDao {
    
	List<EmployeeMod> getEmployeeList();
	   	
	EmployeeMod getEmployeeById(String employeeId);
	
	ValidateUser validateUser(LoginCredentials login);
	
	void addEmployee(EmployeeMod employee);
	
	void deleteEmployee(String employeeId);
}
