package org.commonmark.ext.complex.tables.nodes;

public interface TableNode {
	void accept(TableNodeVisitor tableNodeVisitor);
}
