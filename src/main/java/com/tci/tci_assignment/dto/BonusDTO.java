package com.tci.tci_assignment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tci.tci_assignment.dateHandler.DateHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusDTO {

	private String empName;
	private BigDecimal amount;
	private String currency;
	@JsonDeserialize(using = DateHandler.class)
	private LocalDate joiningDate;
	@JsonDeserialize(using = DateHandler.class)
	private LocalDate exitDate;

}
