package com.v2soft.training.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.v2soft.training.dataModel.AddressTypeMod;
import com.v2soft.training.dataModel.EmployeeAddressIdMod;
import com.v2soft.training.dataModel.EmployeeAddressMod;
import com.v2soft.training.dataModel.EmployeeMod;
import com.v2soft.training.dataModel.LoginCredentials;
import com.v2soft.training.dataModel.ValidateUser;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.model.EmployeeLogin;
import com.v2soft.training.model.LoginSession;
import com.v2soft.training.model.LoginSessionId;

public class EmployeeDaoImpl implements EmployeeDao {
		
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
	@Transactional
	public ValidateUser validateUser(LoginCredentials login) {
		String username = login.getUsername();
		String password = login.getPassword();
		boolean validated = false;
		EmployeeLogin empLoginSess = null;
		
		Query<EmployeeLogin> subquery = getCurrentSession().createQuery("from EmployeeLogin");
		List<EmployeeLogin> emplogins = subquery.getResultList();
		Employee emp = null;
		
		for(EmployeeLogin emplogin: emplogins) {
			if(emplogin.getId().getUserName().equals(username)) {
				if(emplogin.getPassword().equals(password)) {
					emp = emplogin.getEmployeeinfo();
					validated = true;
					empLoginSess = emplogin;
				}
			}
		}
		
		EmployeeMod employeeMod = new EmployeeMod();
		ValidateUser user = new ValidateUser();
		
		if(validated && emp != null) {
			employeeMod.setEmployeeId(emp.getEmployeeId());
	        employeeMod.setFirstName(emp.getFirstName());
	        employeeMod.setLastName(emp.getLastName());
	        employeeMod.setMiddleName(emp.getMiddleName());
	        employeeMod.setDateOfBirth(emp.getDateOfBirth());
	        employeeMod.setPassportNumber(emp.getPassportNumber());
	        employeeMod.setSsn(emp.getSsn());
	        Set<EmployeeAddressMod> employeeAddresses = new HashSet<EmployeeAddressMod>();
	        
	        for(EmployeeAddress empAddress:emp.getEmployeeAddresses()) {
	        	EmployeeAddressMod employeeAddressInfo = new EmployeeAddressMod();
	        	
	        	AddressTypeMod AddressTypeInfo = new AddressTypeMod();
	        	AddressTypeInfo.setId(empAddress.getAddressType().getId());
	        	AddressTypeInfo.setType(empAddress.getAddressType().getType());
	        	
	        	EmployeeAddressIdMod employeeAddressIdInfo = new EmployeeAddressIdMod();
	        	employeeAddressIdInfo.setAddressTypeId(empAddress.getId().getAddressTypeId());
	        	employeeAddressIdInfo.setEmployeeId(empAddress.getId().getEmployeeId());
	        	
	        	employeeAddressInfo.setId(employeeAddressIdInfo);
	        	employeeAddressInfo.setAddressType(AddressTypeInfo);
	        	employeeAddressInfo.setAddressLineOne(empAddress.getAddressLine1());
	        	employeeAddressInfo.setAddressLineTwo(empAddress.getAddressLine2());
	        	employeeAddressInfo.setCity(empAddress.getCity());
	        	employeeAddressInfo.setState(empAddress.getState());
	        	employeeAddressInfo.setZipcode(empAddress.getZipCode());
	        	employeeAddressInfo.setZipfour(empAddress.getZip4());
	        	employeeAddresses.add(employeeAddressInfo);
	        }
	        
	        employeeMod.setEmployeeAddresses(employeeAddresses);
	        
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String dbQuery = "update LoginSession set status='T', logoutTime='" + dtf.format(now) + "'"
	        		+ " where username='" + login.getUsername() +"'";
	        int result = getCurrentSession().createQuery(dbQuery).executeUpdate();
	        
	        //Add session to table
	        UUID uuid = UUID.randomUUID();
	        LoginSessionId lsi = new LoginSessionId();
	        lsi.setEmployeeId(employeeMod.getEmployeeId());
	        lsi.setUserName(username);
	        lsi.setLoginSessionId(uuid.toString());
	        
	        Date datetimeNow = new Date(Calendar.getInstance().getTime().getTime());
	        
	        LoginSession sess = new LoginSession();
	        sess.setId(lsi);
	        sess.setLogin(empLoginSess);
	        sess.setCreatedBy("system");
	        sess.setCreatedTime(datetimeNow);
	        sess.setUpdatedBy("system");
	        sess.setLoginTime(datetimeNow);
	        sess.setStatus("A");
	        
	        getCurrentSession().persist(sess);
	        
	        user.setEmp(employeeMod);
	        user.setUuid(uuid);
	        
		}
		
		return user;
	}
	
    
	@Transactional
    public List<EmployeeMod> getEmployeeList() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		query.select(root);
		Query<Employee> q = getCurrentSession().createQuery(query);		
		return convertEmployeeListToEmployeeModList(q.getResultList());
    }
	
	@Transactional
	public EmployeeMod getEmployeeById(String employeeId) {
		EmployeeMod employeeMod = new EmployeeMod();
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		query.select(root).where(builder.equal(root.get("employeeId"), employeeId));
		Query<Employee> q = getCurrentSession().createQuery(query);
        Employee emp = q.getSingleResult();
        
        employeeMod.setEmployeeId(emp.getEmployeeId());
        employeeMod.setFirstName(emp.getFirstName());
        employeeMod.setLastName(emp.getLastName());
        employeeMod.setMiddleName(emp.getMiddleName());
        employeeMod.setDateOfBirth(emp.getDateOfBirth());
        employeeMod.setPassportNumber(emp.getPassportNumber());
        employeeMod.setSsn(emp.getSsn());
        Set<EmployeeAddressMod> employeeAddresses = new HashSet<EmployeeAddressMod>();
        
        for(EmployeeAddress empAddress:emp.getEmployeeAddresses()) {
        	EmployeeAddressMod employeeAddressInfo = new EmployeeAddressMod();
        	
        	AddressTypeMod AddressTypeInfo = new AddressTypeMod();
        	AddressTypeInfo.setId(empAddress.getAddressType().getId());
        	AddressTypeInfo.setType(empAddress.getAddressType().getType());
        	
        	EmployeeAddressIdMod employeeAddressIdInfo = new EmployeeAddressIdMod();
        	employeeAddressIdInfo.setAddressTypeId(empAddress.getId().getAddressTypeId());
        	employeeAddressIdInfo.setEmployeeId(empAddress.getId().getEmployeeId());
        	
        	employeeAddressInfo.setId(employeeAddressIdInfo);
        	employeeAddressInfo.setAddressType(AddressTypeInfo);
        	employeeAddressInfo.setAddressLineOne(empAddress.getAddressLine1());
        	employeeAddressInfo.setAddressLineTwo(empAddress.getAddressLine2());
        	employeeAddressInfo.setCity(empAddress.getCity());
        	employeeAddressInfo.setState(empAddress.getState());
        	employeeAddressInfo.setZipcode(empAddress.getZipCode());
        	employeeAddressInfo.setZipfour(empAddress.getZip4());
        	employeeAddresses.add(employeeAddressInfo);
        }
        
        employeeMod.setEmployeeAddresses(employeeAddresses);
		return employeeMod;
	}
	
	private List<EmployeeMod> convertEmployeeListToEmployeeModList(List<Employee> empList) {
		List<EmployeeMod> employeeModList = new ArrayList<EmployeeMod>();
		
		for(int i = 0; i < empList.size(); i++) {
			EmployeeMod mod = new EmployeeMod();
			Employee tmp = empList.get(i);
			
			mod.setEmployeeId(tmp.getEmployeeId());
			mod.setFirstName(tmp.getFirstName());
			mod.setLastName(tmp.getLastName());
			mod.setMiddleName(tmp.getMiddleName());
			mod.setDateOfBirth(tmp.getDateOfBirth());
			mod.setPassportNumber(tmp.getPassportNumber());
			mod.setSsn(tmp.getSsn());
			
			Set<EmployeeAddressMod> empAddressMod = new HashSet<EmployeeAddressMod>();
			for(EmployeeAddress empAddress: tmp.getEmployeeAddresses()) {
				EmployeeAddressMod eam = new EmployeeAddressMod();
				
				EmployeeAddressIdMod eai = new EmployeeAddressIdMod();
				eai.setAddressTypeId(empAddress.getAddressType().getId());
				eai.setEmployeeId(empAddress.getEmployeeinfo().getEmployeeId());
				
				AddressTypeMod atm = new AddressTypeMod();
				atm.setId(empAddress.getAddressType().getId());
				atm.setType(empAddress.getAddressType().getType());
				
				eam.setId(eai);
				eam.setAddressType(atm);
				eam.setAddressLineOne(empAddress.getAddressLine1());
				eam.setAddressLineTwo(empAddress.getAddressLine2());
				eam.setCity(empAddress.getCity());
				eam.setState(empAddress.getState());
				eam.setZipcode(empAddress.getZipCode());
				eam.setZipfour(empAddress.getZip4());
				
				empAddressMod.add(eam);
			}
			mod.setEmployeeAddresses(empAddressMod);
			employeeModList.add(mod);
		}
		
		return employeeModList;
	}

	@Transactional
	public void addEmployee(EmployeeMod employee) {
		
		Employee newEmp = new Employee();
		newEmp.setEmployeeId(employee.getEmployeeId());
		newEmp.setFirstName(employee.getFirstName());
		newEmp.setLastName(employee.getLastName());
		newEmp.setMiddleName(employee.getMiddleName());
		newEmp.setDateOfBirth(employee.getDateOfBirth());
		newEmp.setSsn(employee.getSsn());
		newEmp.setPassportNumber(employee.getPassportNumber());
		getCurrentSession().saveOrUpdate(newEmp);
	}

	@Transactional
	public void deleteEmployee(String employeeId) {
		Query query = getCurrentSession().createQuery("delete from Employee where employeeId = '"+employeeId+"'");
		query.executeUpdate();
	}
}
