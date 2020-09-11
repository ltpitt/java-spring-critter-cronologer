package it.davidenastri.critter.service;

import it.davidenastri.critter.entity.Customer;
import it.davidenastri.critter.entity.Pet;
import it.davidenastri.critter.exception.ResourceNotFoundException;
import it.davidenastri.critter.repository.CustomerRepository;
import it.davidenastri.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Pet findById(long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet not found, ID: " + petId));
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.insertPet(pet);
        customerRepository.save(customer);
        return pet;
    }

    public List<Pet> getPetsByOwner(long customerId) {
        return petRepository.findByCustomer(customerRepository.getOne(customerId));
    }

    public List<Pet> findAllById(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }
}
