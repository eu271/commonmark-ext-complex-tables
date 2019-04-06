package org.commonmark.ext.complex.tables.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.commonmark.ext.complex.tables.ComplexTableBlock;
import org.commonmark.ext.complex.tables.nodes.TableBody;
import org.commonmark.ext.complex.tables.nodes.TableCell;
import org.commonmark.ext.complex.tables.nodes.TableCellLine;
import org.commonmark.ext.complex.tables.nodes.TableHead;
import org.commonmark.ext.complex.tables.nodes.TableNode;
import org.commonmark.ext.complex.tables.nodes.TableNodeVisitor;
import org.commonmark.ext.complex.tables.nodes.TableRow;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;

public abstract class ComplexTableNodeRenderer implements NodeRenderer, TableNodeVisitor {
	@Override
	public Set<Class<? extends Node>> getNodeTypes() {
		return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(//
				ComplexTableBlock.class,//
				TableBody.class,//
				TableHead.class,//
				TableRow.class,//
				TableCell.class,//
				TableCellLine.class)));
	}

	@Override
	public void render(Node node) {
		if (node instanceof TableNode) {
			TableNode tableNode = (TableNode) node;
			tableNode.accept(this);
		}
	}

}
