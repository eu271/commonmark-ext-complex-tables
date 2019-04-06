package org.commonmark.ext.complex.tables.nodes;

import org.commonmark.node.CustomNode;

public class TableHead extends CustomNode implements TableNode {
	@Override
	public void accept(TableNodeVisitor tableNodeVisitor) {
		tableNodeVisitor.visit(this);
	}
}
