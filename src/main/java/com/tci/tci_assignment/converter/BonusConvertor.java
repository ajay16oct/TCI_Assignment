package com.tci.tci_assignment.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tci.tci_assignment.dto.BonusDTO;
import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;
import com.tci.tci_assignment.repository.BonusRepository;
import com.tci.tci_assignment.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BonusConvertor {

	@Autowired
	private BonusRepository bonusRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Bonus DToToEntity(BonusDTO bonusDTO) {

		Bonus bonus = new Bonus();
		Optional<Employee> optEmployee = employeeRepository.getByName(bonusDTO.getEmpName());
		Employee employee = null;

		if (optEmployee.isPresent()) {
			employee = optEmployee.get();
		} else {
			employee = new Employee();
			employee.setName(bonusDTO.getEmpName());
			employee.setJoiningDate(bonusDTO.getJoiningDate());
			employee.setExitDate(bonusDTO.getExitDate());
		}
		bonus.setEmployee(employee);
		bonus.setAmount(bonusDTO.getAmount());
		bonus.setCurrency(bonusDTO.getCurrency());

		List<Bonus> employeeBonuses = employee.getBonuses();
		if (employeeBonuses != null) {
			employeeBonuses.add(bonus);
		} else {
			employeeBonuses = new ArrayList<>();
			employeeBonuses.add(bonus);
		}
		employee.setBonuses(employeeBonuses);
		return bonus;
	}

	public BonusDTO entityToDTO(Bonus bonus) {
		BonusDTO bonusDTO = new BonusDTO();
		bonusDTO.setEmpName(bonus.getEmployee().getName());
		bonusDTO.setCurrency(bonus.getCurrency());
		bonusDTO.setAmount(bonus.getAmount());
		bonusDTO.setExitDate(bonus.getEmployee().getExitDate());
		bonusDTO.setJoiningDate(bonus.getEmployee().getJoiningDate());
		return bonusDTO;
	}

	public List<Bonus> convertListOfDTOToEntity(List<BonusDTO> bonusesDTO) {
		// BonusConvertor bonusConvertor = new BonusConvertor();
		return bonusesDTO.stream().map(e -> DToToEntity(e)).collect(Collectors.toList());
	}
}
