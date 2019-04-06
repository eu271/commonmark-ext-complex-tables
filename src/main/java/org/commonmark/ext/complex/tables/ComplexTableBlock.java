package org.commonmark.ext.complex.tables;

import org.commonmark.ext.complex.tables.nodes.TableNode;
import org.commonmark.ext.complex.tables.nodes.TableNodeVisitor;
import org.commonmark.ext.complex.tables.nodes.internal.CustomBlockWithProperties;
import org.commonmark.node.CustomBlock;

public class ComplexTableBlock extends CustomBlockWithProperties implements TableNode {
	@Override
	public void accept(TableNodeVisitor tableNodeVisitor) {
		tableNodeVisitor.visit(this);
	}
}
