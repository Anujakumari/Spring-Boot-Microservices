package com.profound.address.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profound.address.entity.Address;
import com.profound.address.repository.AddressRepository;
import com.profound.address.response.AddressResponse;

@Service
public class AddressService 
{
	@Autowired
	private AddressRepository addressRepository ;
	
	@Autowired
	private ModelMapper mapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId)
	{
		Optional<Address> addressByEmployeeId = addressRepository.findAddressByEmployeeId(employeeId);
		
		AddressResponse addressResponse= mapper.map(addressByEmployeeId, AddressResponse.class);
		
		return addressResponse;
	}


}
