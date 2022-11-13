package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Employee;

import com.example.demo.service.impl.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empserviceimpl;
	
	@RequestMapping("index")
	public String Emp()
	{
	return "index.jsp";	
	}
	
	@RequestMapping("addEmployee")
	public String addEmployee(Employee employee)
	{
		empserviceimpl.addEmployee(employee);
		return "index.jsp";	
	}
	
	@RequestMapping("getAllEmployee")
	public String getAllEmployee()
	{
		empserviceimpl.getAllEmployee();
		return "index.jsp";	
	}
	
	@RequestMapping("getEmployee")
	public ModelAndView getEmployee(@RequestParam int id)
	{
	    ModelAndView mav=new ModelAndView();
	    mav.setViewName("showEmployee.jsp");
	    Employee employee=empserviceimpl.getEmployee(id);
		mav.addObject(employee);
		return mav;	
	}
	
	@RequestMapping("deleteEmployee")
	public ModelAndView deleteEmployee(@RequestParam int id)
	{
	    ModelAndView mav=new ModelAndView("deleteEmployee.jsp");
	    empserviceimpl.deleteEmployee(id);
		return mav;	
	}
	
	@RequestMapping("updateEmployee")
	public ModelAndView updateEmployee(Employee employee)
	{
	    ModelAndView mav=new ModelAndView("updateEmployee.jsp");
	    employee=empserviceimpl.updateEmployee(employee);
		mav.addObject(employee);
		return mav;	
	}



}
