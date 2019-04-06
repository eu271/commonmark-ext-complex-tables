package org.commonmark.ext.complex.tables;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtensionUseageExample {

	static Parser parser;
	static HtmlRenderer renderer;

	@BeforeClass
	public static void testSetup() {
		List<Extension> extensions = Arrays.asList(ComplexTablesExtension.create());

		parser = Parser.builder()//
				.extensions(extensions)//
				.build();

		// Builder base
		HtmlRenderer.Builder builder = HtmlRenderer.builder()//
				.extensions(extensions)//
				.escapeHtml(true)//
				.softbreak("<br />");

		renderer = builder.build();
	}

	@Test
	public void testExampleUse() throws Exception {

		try (InputStream inputStream = ExtensionUseageExample.class.getResourceAsStream("examples/example1.md"); //
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
			Node node = parser.parseReader(inputStreamReader);

			System.out.println(renderer.render(node));
		}


	}
}
