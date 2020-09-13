package it.davidenastri.critter.repository;

import it.davidenastri.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);

}
