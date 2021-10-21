package org.impelsys.department.controller;

import java.util.Optional;

import org.impelsys.department.model.Department;
import org.impelsys.department.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/department")
@Slf4j  //generate logger instance with default name log
public class DepartmentController {

	@Autowired
	DepartmentService deptService;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department dept)
	{
		log.info("Inside saveDepartment");
		System.out.println("In saveDepartment()");
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Connection", "keep-alive");
//		headers.set("keep-alive", "timeout:20");
//		ResponseEntity<Department> responseEntity = new ResponseEntity(dept,headers,HttpStatus.OK);
		return deptService.saveDepartment(dept);
//		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public Optional<Department> getDepartment(@PathVariable("id") Integer id)
	{
		log.info("Fetching department for id: "+id);
		return deptService.findDepartmentById(id);
	}
}
