package it.davidenastri.critter.repository;

import it.davidenastri.critter.entity.Customer;
import it.davidenastri.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByCustomer(Customer customer);
}
