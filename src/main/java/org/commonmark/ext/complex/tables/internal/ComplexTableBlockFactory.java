package org.commonmark.ext.complex.tables.internal;

import java.util.List;

import org.commonmark.ext.complex.tables.ComplexTableBlock;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

public class ComplexTableBlockFactory extends AbstractBlockParserFactory {

	static final String TABLE_START = "#@+TABLE_START";
	static final String TABLE_END = "#@+TABLE_END";

	public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
		CharSequence currentLine = parserState.getLine();
		CharSequence paragraph = matchedBlockParser.getParagraphContent();


		if (paragraph != null) {
			String stringParagraph = paragraph.toString();

			if (stringParagraph.startsWith(TABLE_START)) {
				ComplexTableBlock complexTableBlock = new ComplexTableBlock();

				return BlockStart.of(new ComplexTableBlockParser(complexTableBlock))//
						.atIndex(parserState.getIndex())//
						.replaceActiveBlockParser();
			}
		}
		return BlockStart.none();
	}
}
