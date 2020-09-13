package it.davidenastri.critter.service;

import it.davidenastri.critter.entity.Employee;
import it.davidenastri.critter.entity.Pet;
import it.davidenastri.critter.entity.Schedule;
import it.davidenastri.critter.repository.CustomerRepository;
import it.davidenastri.critter.repository.EmployeeRepository;
import it.davidenastri.critter.repository.PetRepository;
import it.davidenastri.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private EmployeeRepository employeeRepository;

    private PetRepository petRepository;

    private CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.getAllByEmployeesContains(employeeRepository.getOne(employeeId));
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepository.getAllByPetsContains(petRepository.getOne(petId));
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepository.getAllByPetsIn(customerRepository.getOne(customerId).getPets());
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
