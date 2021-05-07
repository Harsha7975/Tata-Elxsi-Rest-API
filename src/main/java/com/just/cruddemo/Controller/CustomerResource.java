package com.just.cruddemo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.just.cruddemo.Service.CustomerService;
import com.just.cruddemo.models.Customer;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/customer")
public class CustomerResource {


	private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/{customernumber}", produces = "application/json")
	public ResponseEntity<Customer> getCustomer(@PathVariable("customernumber") long id) {
		LOG.info("****** Entered Customercontroller to get the data for a particular ID from Database *******");

		Optional<Customer> response=customerService.getCustomer(id);
		LOG.debug("Customer response :: {}",response);
		return new ResponseEntity<>(response.get(), OK);
	}

	@GetMapping(path = "/",consumes= "application/json", produces = "application/json")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		LOG.info("****** Entered Customercontroller to get complete from Database *******");

		List<Customer> response=customerService.getCustomers();
		LOG.debug("Customer response :: {}",response);
		return new ResponseEntity<>(response, OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {

		LOG.info("****** Entered Customercontroller to Save the data into Database *******");

		String response=customerService.addCustDetails(customer);
		LOG.debug("Customer response :: {}",response);
		return new ResponseEntity<>(response, OK);

	} 
	
	@PutMapping(path="/{customernumber}", produces = "application/json")
    public ResponseEntity<String> updateCustomer(@PathVariable("customernumber") long id, @RequestBody Customer customer) {
		LOG.info("****** Entered Customercontroller to update the data from Database *******");

		String response=customerService.updateCustomer(id,customer);
		LOG.debug("Customer response :: {}",response);
		return new ResponseEntity<>(response, OK);
   }
	
	@DeleteMapping(path = "/{customernumber}", produces = "application/json")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customernumber") long id) {

		LOG.info("****** Entered Customercontroller to delete the data from Database *******");

		String response=customerService.deleteCustomer(id);
		LOG.debug("Customer response :: {}",response);
		return new ResponseEntity<>(response, OK);
     } 
	
	

}
