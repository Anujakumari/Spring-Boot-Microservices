package com.profound.employee.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.profound.employee.entity.Employee;
import com.profound.employee.repository.EmployeeRepository;
import com.profound.employee.response.AddressResponse;
import com.profound.employee.response.EmployeeResponse;

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private WebClient webClient;
	
	// GET
	public EmployeeResponse getEmployeeById(int id)
	{
		// fetch employee entity
		Optional<Employee> employee = employeeRepository.findById(id);
		
		// map employee entity to EmployeeResponse
		EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
		
		// fetch AddressResponse from Address service using webClient
		AddressResponse addressResponse = webClient.get().uri("/address/" + id).retrieve().bodyToMono(AddressResponse.class).block();
		
		// add the fetched AddressResponse to EmployeeResponse
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
		
	}

}
