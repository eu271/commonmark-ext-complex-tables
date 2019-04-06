package org.commonmark.ext.complex.tables.internal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ComplexTableBlockParserTest {


	@Test
	public void testSearchNextLine() {
		ComplexTableBlockParser complexTableBlockParser = new ComplexTableBlockParser(null);
		List<String> lines = new ArrayList<>();
		lines.add("| a | b1 | b2 | b3 |");
		lines.add("| a | b1 | b2 | b3 |");
		lines.add("| a | b1 | b2 | b3 |");

		List<String> row = complexTableBlockParser.searchNextRow(lines);

		Assert.assertEquals("Wrong number of lines in row", 3, row.size());

		lines = new ArrayList<>();
		lines.add("- a | b1 | b2 | b3 |");

		row = complexTableBlockParser.searchNextRow(lines);

		Assert.assertEquals("Wrong number of lines in row", 0, row.size());
	}

}