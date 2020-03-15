package com.axway.qainterview;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DbManager {
    private static final String dbPath = "./my_db.txt";

    public static void saveGraphInDB(Graph graph) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(dbPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Graph.Vertex, List<Graph.Vertex>> adjList = graph.getAdjList();

        String output = "";

        for(Graph.Vertex v : adjList.keySet()) {
            output += v.label;

            for(Graph.Vertex advV : graph.getAdjVertices(v.label)) {
                output += " " + advV.label;
            }

            output += "\n";
        }

        try {
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph loadGraphFromDB() {
        Graph graph = new Graph();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dbPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (line != null) {
            //parse line and insert in graph
            String[] tokens = line.split("\\s");
            graph.addVertex(tokens[0]);

            for (int i = 1;i < tokens.length;i++) {
                graph.addVertex(tokens[i]);
                graph.addEdge(tokens[0], tokens[i]);
            }

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

}
