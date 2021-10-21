package org.impelsys.employeeservice.controller;

import java.util.Optional;

import org.impelsys.employeeservice.model.Employee;
import org.impelsys.employeeservice.service.EmployeeService;
import org.impelsys.employeeservice.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/employee")
@Slf4j  //generate logger instance with default name log
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@PostMapping("/")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp)
	{
		log.info("Inside saveEmployee");
		System.out.println("In saveEmployee()");
	
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "keep-alive");
		headers.set("keep-alive", "timeout:20");
		ResponseEntity<Employee> responseEntity = new ResponseEntity(emp,headers,HttpStatus.OK);
		empService.saveEmployee(emp);
		return responseEntity;
		
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getEmployee(@PathVariable("id") Integer id)
	{
		log.info("Fetching Employee for id: "+id);
		return empService.findEmployeeById(id);
	}
	
	@GetMapping("/withdept/{id}")
	public ResponseVO getEmployeeWithDept(@PathVariable("id") Integer id)
	{
		log.info("Fetching Employee for id: "+id);
		return empService.findEmployeeWithDeptById(id);
	}
	
	@GetMapping("/employee/version")
	public String getVersionInfo(){
			
			return "Employee Service-V1.0";
	}
}
