package com.just.cruddemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.just.cruddemo.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  public Customer findByName(String name);
}
