package org.commonmark.ext.complex.tables.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.xml.ws.Holder;

import org.commonmark.ext.complex.tables.ComplexTableBlock;
import org.commonmark.ext.complex.tables.nodes.TableCell;
import org.commonmark.ext.complex.tables.nodes.TableCellLine;
import org.commonmark.ext.complex.tables.nodes.TableHead;
import org.commonmark.ext.complex.tables.nodes.TableRow;
import org.commonmark.node.Block;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Node;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.ParserState;

public class ComplexTableBlockParser extends AbstractBlockParser {

	private final ComplexTableBlock complexTableBlock;
	private final List<String> tableLines = new ArrayList<>();

	public ComplexTableBlockParser(ComplexTableBlock complexTableBlock) {
		this.complexTableBlock = complexTableBlock;
	}

	public Block getBlock() {
		return complexTableBlock;
	}

	public BlockContinue tryContinue(ParserState parserState) {
		if (parserState.getLine().toString().startsWith(ComplexTableBlockFactory.TABLE_END)) {
			return BlockContinue.finished();
		}

		return BlockContinue.atIndex(parserState.getIndex());
	}

	@Override
	public void addLine(CharSequence line) {
		tableLines.add(line.toString());
	}

	enum LineType {
		ROW,
		CONFIGURATION,
		UNKNOWN,
	}

	enum State {
		CONFIGURATION,
		ROW,
	}

	@Override
	public void parseInlines(InlineParser inlineParser) {
		Node currentNode = new TableRow();

		List<String> rows = new ArrayList<>(tableLines);

//		if (rows.size()==0) {
//			return;
//		}
//
//		String firstLine = rows.get(0);
//		LineType fistLineType = type(firstLine);
//		if (LineType.CONFIGURATION ==  fistLineType) {
//			rows = rows.subList(1, rows.size());
//		}

		Set<Integer> mcm = new HashSet<>();
		List<TableCell> colspanIndexHolders = new ArrayList<>();

		int cells = -1;
		List<Integer> numbers = null;

		complexTableBlock.appendChild(currentNode);

		do {
			String line = rows.get(0);

			switch (type(line)) {
				case ROW:
					RowConfiguration rowConfiguration = new RowConfiguration();
					rowConfiguration.setCells(cells);
					rowConfiguration.setNumbers(numbers);
					List<String> cellStrings = searchNextRow(rows);

					List<TableCell> tableCells = createCells(rowConfiguration, cellStrings, inlineParser);
					for (TableCell tableCell : tableCells) {
						currentNode.appendChild(tableCell);
					}

					colspanIndexHolders.addAll(tableCells);

					rows = rows.subList(cellStrings.size(), rows.size());

					break;
				case CONFIGURATION:
					line = line.substring(1);
					String[] numbersS = line.split(",");
					cells = Arrays.stream(numbersS).map(String::trim).filter(s -> !s.isEmpty()).mapToInt(Integer::valueOf).sum();
					numbers = Arrays.stream(numbersS).map(String::trim).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList());
					mcm.addAll(numbers);
					rows = rows.subList(1, rows.size());
					break;
				case UNKNOWN:
				default:
					//BAD
			}

			if (type(line) == LineType.ROW) {
				currentNode = new TableRow();
				complexTableBlock.appendChild(currentNode);
			}
		} while (!rows.isEmpty());

		int mcmD = mcm.stream().mapToInt(Integer::intValue).reduce((i, i1) -> i * i1).getAsInt();

		for (TableCell tableCell : colspanIndexHolders) {
			tableCell.setColspan(mcmD / tableCell.getColspanIndex());
		}
	}

	List<String> searchNextRow(List<String> table) {
		List<String> row = new ArrayList<>();

		boolean found = false;
		int i = 0;
		do {
			String line = table.get(i);
			LineType lineType = type(line);

			if (lineType == LineType.ROW) {
				row.add(line);
			} else {
				found = true;
			}

			i++;
			if (i >= table.size())
				found = true;

		} while (!found);

		return row;
	}

	List<TableCell> createCells(RowConfiguration rowConfiguration, List<String> cellStrings, InlineParser inlineParser) {
		List<TableCell> cellList = new ArrayList<>();

		int cells = rowConfiguration.getCells();
		int group;
		List<Integer> numbers = rowConfiguration.getNumbers();

		for (int i = 0; i < cellStrings.size(); i++) {
			String line = cellStrings.get(i);
			line = line.substring(1, line.length() - 1);
			cellStrings.set(i, line);
		}
		group = numbers.get(0);
		for (int j = 0; j < cells; j++) {
			TableCell cellNode = new TableCell(numbers.get(0));
			Node lastNode = cellNode;
			for (int i = 0; i < cellStrings.size(); i++) {
				TableCellLine tableCellLine = new TableCellLine();

				String line = cellStrings.get(i);

				String nextLine = null;

				int nextCell = line.indexOf("|");
				if (nextCell == -1)
					nextCell = line.length();
				else
					nextLine = line.substring(nextCell + 1);

				String cell = line.substring(0, nextCell);

				inlineParser.parse(cell.trim(), tableCellLine);

				group--;
				if (group == 0 && numbers.size() > 1) {
					numbers = numbers.subList(1, numbers.size());
					group = numbers.get(0);
				}


				line = nextLine;
				cellStrings.set(i, line);
				lastNode.appendChild(tableCellLine);
				lastNode = tableCellLine;

			}
			cellList.add(cellNode);
		}

		return cellList;
	}

	public LineType type(CharSequence line) {
		char fistChar = line.charAt(0);
		switch (fistChar) {
			case '|':
				return LineType.ROW;
			case '-':
				return LineType.CONFIGURATION;
			default:
				return LineType.UNKNOWN;
		}
	}

}
