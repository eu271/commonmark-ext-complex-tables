package org.commonmark.ext.complex.tables.nodes.internal;

import java.util.HashMap;
import java.util.Map;

import org.commonmark.node.CustomNode;

public class CustomNodeWithProperties extends CustomNode {

	private Map<String, String> properties = new HashMap<>();

	public Map<String, String> getProperties() {
		return properties;
	}
}
