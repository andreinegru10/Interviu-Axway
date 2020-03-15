package com.axway.qainterview;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Rest {
    private static Graph graph;

    public static void initGraph() {
        graph = DbManager.loadGraphFromDB();
        System.out.println("Database loaded");
    }

    public static String help() {
        String response = "";
        response += "get [label]; ";
        response += "del [label1] [label2]; ";
        response += "add label_1 [label_2]; ";
        response += "quit; ";

        return response;
    }

    public static String get(String nodeLabel) {
        String response = "[";
        List<Graph.Vertex> adjVerts = graph.getAdjVertices(nodeLabel);

        if (adjVerts != null) {

            if (adjVerts.size() == 0) {
                response = "[]";
            }
            else {
                for (int i = 0; i < adjVerts.size() - 1; i++) {
                    response += adjVerts.get(i).toString() + ", ";
                }

                response += adjVerts.get(adjVerts.size() - 1).toString() + "]";
            }
        }
        else { //label nu exista
            response = nodeLabel + " not in database";
        }

        return response;
    }

    public static String get() {
        String response = "";
        Map<Graph.Vertex, List<Graph.Vertex>> adjList = graph.getAdjList();
        Set<Graph.Vertex> keySet = adjList.keySet();

        if (keySet.isEmpty()) {
            response = "The database is empty";
        }
        else {
            for (Graph.Vertex v : keySet) {
                response += v.label + "->" + get(v.label) + "; ";
            }
        }

        return response;
    }

    public static String delete() {
        String response = "";

        graph = new Graph();
        DbManager.saveGraphInDB(graph);

        response = "Database deleted successfully";

        return response;
    }

    public static String addNode(String nodeLabel) {
        String response = "";

        if (!graph.hasVertex(nodeLabel)) {
            graph.addVertex(nodeLabel);
            DbManager.saveGraphInDB(graph);
            response = nodeLabel + " added successfully in database";
        }
        else {
            response = nodeLabel + " is already in database";
        }

        return response;
    }

    public static String delNode(String nodeLabel) {
        String response = "";

        if (graph.hasVertex(nodeLabel)) {
            //delete
            graph.removeVertex(nodeLabel);
            DbManager.saveGraphInDB(graph);

            response = nodeLabel + " deleted successfully from database";
        }
        else {
            response = nodeLabel + " is not in database";
        }

        return response;
    }

    public static String addEdge(String nodeLabel1, String nodeLabel2) {
        String response = "";

        if (nodeLabel1.equals(nodeLabel2)) {
            response = "Labels should be different";

            return response;
        }

        if (graph.hasEdge(nodeLabel1, nodeLabel2)) {
            //nu trebuie adaugat nimic
            response = "Edge between " + nodeLabel1 + " and " + nodeLabel2 + " already in database";
        }
        else {
            if (graph.hasVertex(nodeLabel1) && graph.hasVertex(nodeLabel2)) {
                graph.addEdge(nodeLabel1, nodeLabel2);
                DbManager.saveGraphInDB(graph);
                response = "Edge between " + nodeLabel1 + " and " + nodeLabel2 + " added in database";
            }

            if (!graph.hasVertex(nodeLabel1)) {
                response += nodeLabel1 + " not in database; ";
            }

            if (!graph.hasVertex(nodeLabel2)) {
                response += nodeLabel2 + " not in database; ";
            }
        }

        return response;
    }

    public static String delEdge(String nodeLabel1, String nodeLabel2) {
        String response = "";

        if (nodeLabel1.equals(nodeLabel2)) {
            response = "Labels should be different";

            return response;
        }

        if (graph.hasEdge(nodeLabel1, nodeLabel2)) {
            graph.removeEdge(nodeLabel1, nodeLabel2);
            DbManager.saveGraphInDB(graph);
            response = "Edge between " + nodeLabel1 + " and " + nodeLabel2 + " removed from database";
        }
        else {
            response = "Edge between " + nodeLabel1 + " and " + nodeLabel2 + " not in database";
        }

        return response;
    }
}
