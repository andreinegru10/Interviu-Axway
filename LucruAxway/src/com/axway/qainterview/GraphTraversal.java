package com.axway.qainterview;

import java.util.*;

public class GraphTraversal {

	public static Set<String> breadthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
				if (!visited.contains(v.label)) {
					visited.add(v.label);
					queue.add(v.label);
				}
			}
		}
		return visited;
	}

	private static void dfsAux(Graph graph, String root, Set<String> visited) {
		visited.add(root);

		for (Graph.Vertex v : graph.getAdjVertices(root)) {
			if (!visited.contains(v.label)) {
				dfsAux(graph, v.label, visited);
			}
		}
	}

	public static Set<String> depthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<>();

		dfsAux(graph, root, visited);

		return visited;
	}

}
