package com.tci.tci_assignment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;
import com.tci.tci_assignment.excpetion.BonusException;
import com.tci.tci_assignment.repository.BonusRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BonusCalculatorService implements BonusCalculator {

	private final EmployeeMapper mapperService;
	private final BonusRepository bonusRepository;

	@Override
	public Map<String, List<Employee>> calculate(List<Bonus> bonuses) throws BonusException {

		try {
			bonusRepository.saveAll(bonuses);
			return mapperService
					.map(bonuses.stream()
							.filter(t -> t.getEmployee().getJoiningDate().isBefore(LocalDate.now())
									&& t.getEmployee().getExitDate().isAfter(LocalDate.now()))
							.collect(Collectors.toList()));
		} catch (Exception e) {
			throw new BonusException(e.getMessage());
		}

	}

}
