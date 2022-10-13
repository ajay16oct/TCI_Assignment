package com.tci.tci_assignment.service;

import java.util.List;
import java.util.Map;

import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;

public interface EmployeeMapper {

	public Map<String, List<Employee>> map(List<Bonus> bonuses);
}
