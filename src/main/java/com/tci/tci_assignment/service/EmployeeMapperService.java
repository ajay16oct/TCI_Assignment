package com.tci.tci_assignment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;

@Service
public class EmployeeMapperService implements EmployeeMapper {

	@Override
	public Map<String, List<Employee>> map(List<Bonus> bonuses) {

		Map<String, List<Employee>> mapOfCurrencyToEmployee = new HashMap<>();
		for (Bonus bonus : bonuses) {
			if (mapOfCurrencyToEmployee.containsKey(bonus.getCurrency())) {
				mapOfCurrencyToEmployee.get(bonus.getCurrency()).add(bonus.getEmployee());
			} else {

				List<Employee> listOfEmployees = new ArrayList<>();
				listOfEmployees.add(bonus.getEmployee());
				mapOfCurrencyToEmployee.put(bonus.getCurrency(), listOfEmployees);
			}
		}

		for (Map.Entry<String, List<Employee>> entry : mapOfCurrencyToEmployee.entrySet()) {
			Collections.sort(entry.getValue(), new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {

					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		return mapOfCurrencyToEmployee;
	}
}