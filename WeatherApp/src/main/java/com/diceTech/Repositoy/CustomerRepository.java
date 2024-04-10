package com.diceTech.Repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diceTech.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Optional<Customer> findByEmail(String email);
}