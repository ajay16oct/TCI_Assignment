package com.tci.tci_assignment.wrapper;

import java.util.List;

public class Wrapper<T> {

	private List<T> bonus;

	public Wrapper() {
		super();
	}

	public List<T> getBonus() {
		return bonus;
	}

	public void setBonus(List<T> bonus) {
		this.bonus = bonus;
	}

}
