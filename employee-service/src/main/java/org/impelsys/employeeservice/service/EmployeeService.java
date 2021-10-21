package org.impelsys.employeeservice.service;


import java.util.Optional;

import org.impelsys.employeeservice.model.Employee;
import org.impelsys.employeeservice.repository.EmployeeRepository;
import org.impelsys.employeeservice.vo.Department;
import org.impelsys.employeeservice.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	
	@Value("${deptservice_endpoint}")
	String deptServiceEndpoint;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EmployeeRepository empRepo;

	public Employee saveEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	public Optional<Employee> findEmployeeById(Integer id) {
		
		return empRepo.findById(id);  //eager  //from database
		//return empRepo.getById(id);   //lazy -> gives ref to dummy employee object
		
	}

	public ResponseVO findEmployeeWithDeptById(Integer id) {
		ResponseVO responseVO = new ResponseVO();
		Employee emp = empRepo.findById(id).get();
		//need to call a different microservice to get dept
		ResponseEntity<Department> deptResponse = restTemplate.getForEntity(deptServiceEndpoint + emp.getDeptId(), Department.class);
		log.info("deptResponse: "+deptResponse);
		responseVO.setEmployee(emp);
		responseVO.setDepartment(deptResponse.getBody());
		return responseVO;
	}

}
