package com.tci.tci_assignment.service;

import java.util.List;
import java.util.Map;

import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;
import com.tci.tci_assignment.excpetion.BonusException;

public interface BonusCalculator {

	public Map<String, List<Employee>> calculate(List<Bonus> bonus) throws BonusException;
}
