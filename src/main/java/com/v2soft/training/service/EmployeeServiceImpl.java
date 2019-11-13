package com.v2soft.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v2soft.training.dao.EmployeeDao;
import com.v2soft.training.dataModel.EmployeeAddressMod;
import com.v2soft.training.dataModel.EmployeeBasicInfo;
import com.v2soft.training.dataModel.EmployeeMod;
import com.v2soft.training.dataModel.LoginCredentials;
import com.v2soft.training.dataModel.ValidateUser;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
    
	@Transactional
	@Override
    public List<EmployeeMod> getEmployeeList() {
		return employeeDao.getEmployeeList();
    }
	
	@Transactional
	@Override
	public EmployeeMod getEmployeeById(String employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}
	
	@Transactional
	@Override
	public ValidateUser validateUser(LoginCredentials login) {
		return employeeDao.validateUser(login);
	}
	
	@Transactional
	@Override
	public void addEmployee(EmployeeMod employee) {
		employeeDao.addEmployee(employee);		
	}
	
	@Transactional
	@Override
	public void deleteEmployee(String employeeId) {
		employeeDao.deleteEmployee(employeeId);
		
	}
}