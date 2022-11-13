package com.example.demo.service;

import com.example.demo.entity.Employee;

public interface EmployeeService {
	
	public Employee addEmployee(Employee employee);
	public Iterable<Employee> getAllEmployee();
	public Employee getEmployee(int id);
	public Employee deleteEmployee( int id);
	public Employee updateEmployee(Employee employee);

}
