package org.commonmark.ext.complex.tables.nodes;

import org.commonmark.ext.complex.tables.nodes.internal.CustomNodeWithProperties;

public class TableCellLine extends CustomNodeWithProperties implements TableNode {

	@Override
	public void accept(TableNodeVisitor tableNodeVisitor) {
		tableNodeVisitor.visit(this);
	}
}
