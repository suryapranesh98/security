package com.example.demo.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.entity.Employee;
import com.example.demo.repos.EmployeeRepos;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepos emprepo;
	
	
	public Employee addEmployee(Employee employee)
	{
	    return emprepo.save(employee);
	}
	
	public Iterable<Employee> getAllEmployee()
	{
		return emprepo.findAll();	
	}
	
	public Employee getEmployee(int id)
	{
	 return emprepo.findById(id).orElse(new Employee());
			
	}
	
	
	public Employee deleteEmployee( int id)
	{
	    
		Employee employee=emprepo.findById(id).orElse(new Employee());
	    emprepo.deleteById(id);
		return employee;	
	}
	
	@RequestMapping("updateEmployee")
	public Employee updateEmployee(Employee employee)
	{
	 
	   Employee existingemployee=emprepo.findById(employee.getId()).orElse(new Employee());
	   employee.setId(employee.getId());
	   employee.setName(employee.getName());
	   employee.setEmail(employee.getEmail());
	   return emprepo.save(existingemployee);
		
	}
}
