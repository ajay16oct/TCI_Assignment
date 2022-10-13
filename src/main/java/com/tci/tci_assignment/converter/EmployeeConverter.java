package com.tci.tci_assignment.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tci.tci_assignment.dto.EmployeeDTO;
import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;

@Component
public class EmployeeConverter {

	public List<EmployeeDTO> entityToDTO(Employee employee) {

		List<Bonus> bonuses = employee.getBonuses();

		List<EmployeeDTO> employeeDTOs = bonuses.stream().map(e -> new EmployeeDTO(employee.getName(), e.getAmount()))
				.collect(Collectors.toList());
		return employeeDTOs;
	}

	public List<EmployeeDTO> entityToDTO(List<Employee> employees) {

		return employees.stream().map(e -> entityToDTO(e)).flatMap(List::stream).collect(Collectors.toList());

	}

	public Employee dTOToEntity(EmployeeDTO employeeDto) {
		Employee employee = new Employee();
		employee.setName(employeeDto.getName());
		// employee.setAmount(employeeDto.getAmount());
		return employee;
	}

}
