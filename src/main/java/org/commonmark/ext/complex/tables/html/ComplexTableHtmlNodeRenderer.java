package org.commonmark.ext.complex.tables.html;

import java.util.HashMap;

import org.commonmark.ext.complex.tables.ComplexTableBlock;
import org.commonmark.ext.complex.tables.internal.ComplexTableNodeRenderer;
import org.commonmark.ext.complex.tables.nodes.TableBody;
import org.commonmark.ext.complex.tables.nodes.TableCell;
import org.commonmark.ext.complex.tables.nodes.TableCellLine;
import org.commonmark.ext.complex.tables.nodes.TableHead;
import org.commonmark.ext.complex.tables.nodes.TableRow;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

public class ComplexTableHtmlNodeRenderer extends ComplexTableNodeRenderer {

	private final HtmlWriter htmlWriter;
	private final HtmlNodeRendererContext context;

	public ComplexTableHtmlNodeRenderer(HtmlNodeRendererContext context) {
		this.htmlWriter = context.getWriter();
		this.context = context;
	}

	@Override
	public void visit(ComplexTableBlock complexTableBlock) {
		htmlWriter.line();
		htmlWriter.tag("table");
		renderChildren(complexTableBlock);
		htmlWriter.tag("/table");
		htmlWriter.line();
	}

	@Override
	public void visit(TableBody tableBody) {
		htmlWriter.raw("TableBody");
	}

	@Override
	public void visit(TableHead tableHead) {
		htmlWriter.raw("TableHead");
	}

	@Override
	public void visit(TableRow tableRow) {
		htmlWriter.line();
		htmlWriter.tag("tr");
		renderChildren(tableRow);
		htmlWriter.tag("/tr");
		htmlWriter.line();

	}

	@Override
	public void visit(TableCell tableCell) {
		htmlWriter.line();
		htmlWriter.tag("td", new HashMap<String, String>() {{
			put("colspan", String.valueOf(tableCell.getColspan()));
		}});
		renderChildren(tableCell);
		htmlWriter.tag("/td");
		htmlWriter.line();
	}

	@Override
	public void visit(TableCellLine tableCellLine) {
		htmlWriter.tag("/br");
		renderChildren(tableCellLine);


	}

	private void renderChildren(Node parent) {
		Node node = parent.getFirstChild();
		while (node != null) {
			Node next = node.getNext();
			context.render(node);
			node = next;
		}
	}
}
