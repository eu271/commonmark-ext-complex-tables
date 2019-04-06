package org.commonmark.ext.complex.tables.nodes.internal;

import java.util.HashMap;
import java.util.Map;

import org.commonmark.ext.complex.tables.nodes.TableNodeVisitor;
import org.commonmark.node.CustomBlock;

public class CustomBlockWithProperties extends CustomBlock {

	private Map<String, String> properties = new HashMap<>();

	public Map<String, String> getProperties() {
		return properties;
	}
}
