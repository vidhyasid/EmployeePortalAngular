package com.v2soft.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v2soft.training.dataModel.EmployeeMod;
import com.v2soft.training.dataModel.LoginCredentials;
import com.v2soft.training.dataModel.ValidateUser;
import com.v2soft.training.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	//private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/getEmployeeList")
    public List<EmployeeMod> getEmployeeList() {
        return (employeeService.getEmployeeList());
    }
	
	@GetMapping("/getEmployee")
    public EmployeeMod getEmployee(@RequestParam(name = "employeeId") String employeeId) {
        return (employeeService.getEmployeeById(employeeId));
    }
	
	@GetMapping("/login")
    public Boolean loginValidation(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password) {
		LoginCredentials login = new LoginCredentials();
		login.setUsername(username);
		login.setPassword(password);
		ValidateUser user = employeeService.validateUser(login);
        EmployeeMod emp = user.getEmp();       
        if(emp != null) {
        	System.out.println("is valid");
        	return true;
        }
        else {
        	System.out.println("is not valid");
        	return false;
        }		
    }
	
	@PostMapping("/addEmployee")
    void addEmployee(@RequestBody EmployeeMod employee) {
        employeeService.addEmployee(employee);
    }
	
	@PostMapping("/deleteEmployee")
    void deleteEmployee(@RequestBody String employeeId) {
		System.out.println("inside delete request");
        employeeService.deleteEmployee(employeeId);
    }
}