package com.profound.address.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.profound.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{

	@Query(
			nativeQuery = true,
			value = "select ea.id, ea.city, ea.state from address ea join employee e on e.id = ea.emp_id where ea.emp_id =:employeeId")
			
	Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
	

}
