package org.commonmark.ext.complex.tables.nodes;

import org.commonmark.ext.complex.tables.ComplexTableBlock;
import org.commonmark.ext.complex.tables.nodes.internal.CustomBlockWithProperties;
import org.commonmark.ext.complex.tables.nodes.internal.CustomNodeWithProperties;

public interface TableNodeVisitor {
	void visit(ComplexTableBlock complexTableBlock);
	void visit(TableBody tableBody);
	void visit(TableHead tableHead);
	void visit(TableRow tableRow);
	void visit(TableCell tableCell);
	void visit(TableCellLine tableCellLine);
}
