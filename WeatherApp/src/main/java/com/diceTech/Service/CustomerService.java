package com.diceTech.Service;

import java.util.List;

import com.diceTech.Exception.CustomerException;
import com.diceTech.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;

}
