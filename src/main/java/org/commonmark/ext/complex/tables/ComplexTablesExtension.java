package org.commonmark.ext.complex.tables;

import org.commonmark.Extension;
import org.commonmark.ext.complex.tables.html.ComplexTableHtmlNodeRenderer;
import org.commonmark.ext.complex.tables.internal.ComplexTableBlockFactory;
import org.commonmark.parser.Parser;
import org.commonmark.parser.Parser.Builder;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;

public class ComplexTablesExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension, TextContentRenderer.TextContentRendererExtension {

	private ComplexTablesExtension() {
	}

	public static Extension create() {
		return new ComplexTablesExtension();
	}

	public void extend(Parser.Builder parserBuilder) {
		parserBuilder.customBlockParserFactory(new ComplexTableBlockFactory());
	}

	public void extend(HtmlRenderer.Builder rendererBuilder) {
		rendererBuilder.nodeRendererFactory(ComplexTableHtmlNodeRenderer::new);
	}

	public void extend(TextContentRenderer.Builder builder) {

	}
}