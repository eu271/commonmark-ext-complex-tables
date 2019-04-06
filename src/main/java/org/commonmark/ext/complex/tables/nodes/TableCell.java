package org.commonmark.ext.complex.tables.nodes;

import org.commonmark.node.CustomNode;

public class TableCell extends CustomNode implements TableNode {

	private final int colspanIndex;
	private int colspan;

	public TableCell(int colspanIndex) {
		this.colspanIndex = colspanIndex;
	}

	public int getColspanIndex() {
		return colspanIndex;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	@Override
	public void accept(TableNodeVisitor tableNodeVisitor) {
		tableNodeVisitor.visit(this);
	}
}
