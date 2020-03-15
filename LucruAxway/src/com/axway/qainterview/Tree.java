package com.axway.qainterview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tree extends Graph {

    public Tree() {
        super();
    }

    private boolean isCyclic(Vertex v, Map<Vertex, Boolean> visited, Vertex parent) {
        visited.replace(v, true);
        Vertex adjV;

        Iterator<Vertex> it = getAdjVertices(v.label).iterator();

        while (it.hasNext()) {
            adjV = it.next();

            if (!visited.get(adjV)) {
                if (isCyclic(adjV, visited, v)) {
                    return true;
                }
            }
            else if (!adjV.equals(parent)) {
                return true;
            }
        }

        return false;
    }

    private boolean isTree() {

        Map<Vertex, Boolean> visited = new HashMap<>();

        for(Vertex v : super.getAdjList().keySet()) {
            visited.put(v, false);
        }

        Vertex root = super.getAdjList().keySet().iterator().next();

        if (isCyclic(root, visited, new Vertex("dummy"))) {
            return false;
        }

        for(Vertex v : super.getAdjList().keySet()) {
            if (!visited.get(v)) {
                return false;
            }
        }

        return true;
    }

    private boolean isLeaf(String label) {
        if (!super.hasVertex(label)) {
            return false;
        }

        int adjCount = super.getAdjVertices(label).size();

        return adjCount == 1;
    }

    @Override
    public void addEdge(String label1, String label2) {

        boolean v1 = hasVertex(label1);
        boolean v2 = hasVertex(label2);

        super.addVertex(label1);
        super.addVertex(label2);

        super.addEdge(label1, label2);

        boolean isTree = isTree();

        if (isTree) {
            //prin adaugare edge s-a mentinut structura de arbore
            System.out.println("Edge inserted successfully");
        }
        else {
            //nu se adauga edge-ul;se strica structura de arbore
            super.removeEdge(label1, label2);

            if (!v1) {
                super.removeVertex(label1);
            }

            if (!v2) {
                super.removeVertex(label2);
            }

            System.out.println("Can not insert the edge. The graph will no longer be a tree");
        }
    }

    @Override
    public void removeEdge(String label1, String label2) {
        boolean l1 = isLeaf(label1);
        boolean l2 = isLeaf(label2);

        if (super.hasEdge(label1, label2)) {
            if (l1) {
                super.removeVertex(label1);
            }

            if (l2) {
                super.removeVertex(label2);
            }

            if (!l1 && !l2) {
                System.out.println("None of the labels is a leaf");
            }
            else {
                System.out.println("Edge removed successfully");
            }
        }
        else {
            System.out.println("No edge between " + label1 + " and " + label2);
        }
    }

    @Override
    public void addVertex(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeVertex(String label) {
        throw new UnsupportedOperationException();
    }
}
