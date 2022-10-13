package com.tci.tci_assignment.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tci.tci_assignment.converter.BonusConvertor;
import com.tci.tci_assignment.converter.EmployeeConverter;
import com.tci.tci_assignment.dto.BonusDTO;
import com.tci.tci_assignment.dto.EmployeeDTO;
import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;
import com.tci.tci_assignment.excpetion.BonusException;
import com.tci.tci_assignment.service.BonusCalculator;
import com.tci.tci_assignment.wrapper.Wrapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BonusController {

	private final BonusCalculator bonusCalculatorService;

	private final BonusConvertor bonusConvertor;

	@PostMapping("/tci/bonus/eligibility")
	public Map<String, List<EmployeeDTO>> getEmployeeDTO(@RequestBody Wrapper<BonusDTO> wrappedBonus)
			throws BonusException {

		try {
			List<BonusDTO> bonusDTOs = wrappedBonus.getBonus();
			List<Bonus> bonuses = bonusConvertor.convertListOfDTOToEntity(bonusDTOs);
			Map<String, List<Employee>> mapOfCurrencyToEmployee = bonusCalculatorService.calculate(bonuses);
			Map<String, List<EmployeeDTO>> mapOfCurrencyToEmployeeDT0 = getMapOfCurrencyTOEmployeeDTO(
					mapOfCurrencyToEmployee);
			return mapOfCurrencyToEmployeeDT0;
		} catch (Exception e) {
			throw new BonusException(e.getMessage());
		}
	}

	private Map<String, List<EmployeeDTO>> getMapOfCurrencyTOEmployeeDTO(
			Map<String, List<Employee>> mapOfCurrencyToEmployee) {
		EmployeeConverter employeeConvertor = new EmployeeConverter();

		return mapOfCurrencyToEmployee.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> employeeConvertor.entityToDTO(e.getValue())));

	}
}
