package it.davidenastri.critter.service;

import it.davidenastri.critter.dto.EmployeeRequestDTO;
import it.davidenastri.critter.entity.Employee;
import it.davidenastri.critter.exception.ResourceNotFoundException;
import it.davidenastri.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found, ID: " + employeeId));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return employeeRepository.getAllByDaysAvailableContains(employeeDTO.getDate().getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(employeeDTO.getSkills()))
                .collect(Collectors.toList());
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found, ID: " + employeeId));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

}
