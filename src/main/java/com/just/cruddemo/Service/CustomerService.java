package com.just.cruddemo.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.just.cruddemo.Controller.CustomerResource;
import com.just.cruddemo.Repository.CustomerRepository;
import com.just.cruddemo.exception.CustomerNotFoundException;
import com.just.cruddemo.models.Customer;



@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	public String addCustDetails(Customer customer) {
		String message="";
		try {
			LOG.info("Entered into service adddetails method");
			if(customerRepository.findByName(customer.getName())!=null) {
				message="Name is already present,please use different name";
			}else {
				customerRepository.save(customer);
				message="Details are saved successfully";
			}

		}catch (Exception e) {
			message="Details are not saved successfully";
		}
		return message;
	}

	public Optional<Customer> getCustomer(long id) {
		Optional<Customer> customerDetails=customerRepository.findById(id);
		if(!(customerDetails.isPresent())){
			throw new CustomerNotFoundException("There is no data for this Id!!!");
		} else {
			return customerDetails;
		}
	}

	public List<Customer> getCustomers() {
		List<Customer> customerDetails=customerRepository.findAll();
		if(customerDetails.isEmpty()){
			throw new CustomerNotFoundException("There is no data in Database!!!");
		} else {
			return customerDetails;
		}

	}

	public String updateCustomer(long id,Customer customer) {
		String message="";
        if(customerRepository.findById(id).isPresent()){
			Customer cust=new Customer();
			cust.setId(id);
			cust.setName(customer.getName());
			cust.setAge(customer.getAge());
			customerRepository.save(cust);
			return message="Update  is  happened Successfully!!!";
		}else {
			throw new CustomerNotFoundException("There is no id to update!!!");
		}
	}


	public String deleteCustomer(long id) {
		String message="";
		if(customerRepository.findById(id).isPresent()){
			customerRepository.deleteById(id);
			return 	message="Delete is  happened Successfully!!!";
		}else {
			throw new CustomerNotFoundException("There is no id to delete!!!");
		}
	}


}


