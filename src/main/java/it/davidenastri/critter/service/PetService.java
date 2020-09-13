package it.davidenastri.critter.service;

import it.davidenastri.critter.entity.Customer;
import it.davidenastri.critter.entity.Pet;
import it.davidenastri.critter.exception.ResourceNotFoundException;
import it.davidenastri.critter.repository.CustomerRepository;
import it.davidenastri.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    private CustomerRepository customerRepository;

    private PetRepository petRepository;

    public PetService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Pet findById(long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet not found, ID: " + petId));
    }

    public Customer findOwnerByPetId(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet not found, ID: " + petId));
        return pet.getCustomer();
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
