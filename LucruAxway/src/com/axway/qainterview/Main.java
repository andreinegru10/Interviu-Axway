package com.axway.qainterview;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Main {

	static Graph graph;
	static Tree tree;

	public static void initGraph() {
		graph = new Graph();
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addVertex("8");
		graph.addVertex("9");

		graph.addEdge("1", "2");
		graph.addEdge("1", "9");
		graph.addEdge("9", "7");
		graph.addEdge("3", "4");
		graph.addEdge("3", "5");
		graph.addEdge("3", "6");
		graph.addEdge("6", "7");
		graph.addEdge("5", "8");
		graph.addEdge("7", "8");

	}

	public static void initTree() {
		tree = new Tree();

		tree.addEdge("1", "2");
		tree.addEdge("3", "4");
		tree.addEdge("1", "3");
		tree.addEdge("3", "4");
		tree.removeEdge("1", "4");
		tree.removeEdge("1", "3");
		tree.removeEdge("3", "4");
	}

	public static void Test1() {
		initGraph();
		System.out.println(graph.printGraph());
	}

	public static void Test2() {
		initGraph();
		Set<String> traversal = GraphTraversal.depthFirstTraversal(graph, "1");

		Iterator<String> it = traversal.iterator();
		String output = "";

		while(it.hasNext()) {
			output += it.next() + " ";
		}

		System.out.println(output);
	}

	public static void Test3() {
		initGraph();

		//graf initial
		System.out.println(graph.printGraph());
		DbManager.saveGraphInDB(graph);
		graph = DbManager.loadGraphFromDB();

		//graf restaurat
		System.out.println(graph.printGraph());
	}

	public static void Test4() {
		initTree();
		System.out.println(tree.printGraph());
	}

	public static void main(String[] args) throws IOException {

		//Test1();
		//Test2();
		//Test3();
		//Test4();
	}
}
