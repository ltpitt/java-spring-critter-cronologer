package it.davidenastri.critter.service;

import it.davidenastri.critter.entity.Customer;
import it.davidenastri.critter.exception.ResourceNotFoundException;
import it.davidenastri.critter.repository.CustomerRepository;
import it.davidenastri.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    private PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer findById(long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found, ID: " + customerId));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
