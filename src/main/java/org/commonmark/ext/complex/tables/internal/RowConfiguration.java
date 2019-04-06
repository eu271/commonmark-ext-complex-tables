package org.commonmark.ext.complex.tables.internal;

import java.util.List;

public class RowConfiguration {
	private int cells = -1;
	private List<Integer> numbers = null;

	public int getCells() {
		return cells;
	}

	public void setCells(int cells) {
		this.cells = cells;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
}
