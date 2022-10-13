package com.tci.tci_assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tci.tci_assignment.entity.Bonus;
import com.tci.tci_assignment.entity.Employee;

@SpringBootTest
class BonusCalculatorTest {

	@MockBean
	private EmployeeMapper employeeMapperService;

	@BeforeEach
	void setUp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

		Employee e1 = new Employee().builder().name("ajay").joiningDate(LocalDate.parse("May-03-2021", formatter))
				.exitDate(LocalDate.parse("May-02-2023", formatter)).id(1L).build();
		Bonus b1 = new Bonus().builder().amount(new BigDecimal(500)).currency("INR").employee(e1).id(1L).build();
		List<Bonus> bonuses = new ArrayList<>();
		bonuses.add(b1);
		e1.setBonuses(bonuses);

		List<Employee> listOfEmployee = new ArrayList<>();
		listOfEmployee.add(e1);
		Map<String, List<Employee>> map = new HashMap<>();
		map.put("INR", listOfEmployee);

		Mockito.when(employeeMapperService.map(bonuses)).thenReturn(map);
	}

	@Test
	public void testCalulate() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

		Employee e1 = new Employee().builder().name("ajay").joiningDate(LocalDate.parse("May-03-2021", formatter))
				.exitDate(LocalDate.parse("May-02-2023", formatter)).id(1L).build();
		Bonus b1 = new Bonus().builder().amount(new BigDecimal(500)).currency("INR").employee(e1).id(1L).build();
		List<Bonus> bonuses = new ArrayList<>();
		bonuses.add(b1);
		e1.setBonuses(bonuses);

		List<Employee> listOfEmployee = new ArrayList<>();
		listOfEmployee.add(e1);
		Map<String, List<Employee>> map = new HashMap<>();
		map.put("INR", listOfEmployee);

		Map<String, List<Employee>> testMap = employeeMapperService.map(bonuses);
		assertEquals(testMap.get("INR"), map.get("INR"));

	}

}
