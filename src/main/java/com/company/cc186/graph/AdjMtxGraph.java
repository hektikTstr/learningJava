package com.company.cc186.graph;

import java.util.LinkedList;
import java.util.Queue;

public class AdjMtxGraph {
    private Vertex[] vertices;
    private int[][] adjMatrix;

    public static class Vertex {
        public int indegree;
        public String name;

        public Vertex(String name) {
            this.name = name;
        }
    }

    public AdjMtxGraph(String[] projectNames, String[][] projectDependencies) {
        insertVertices(projectNames);
        insertEdges(projectDependencies);
    }

    private void insertVertices(String[] names) {
        vertices = new Vertex[names.length];
        for (int i = 0; i < names.length; i++) {
            vertices[i] = new Vertex(names[i]);
        }
        adjMatrix = new int[names.length][names.length];
        // resize is not needed.
        // Vertex[] newVertices;
        // int[][] newAdjMatrix;
        // int newLength;
        // int originalLength;
        // if (vertices != null) {
        //     originalLength = vertices.length;
        //     newLength = originalLength + name.length;
        //     newVertices = new Vertex[newLength];
        //     for (int i = 0; i < vertices.length; i++) {
        //         newVertices[i] = vertices[i];
        //     }
        //     newAdjMatrix = new int[newLength][newLength];
        //     for (int i = 0; i < originalLength; i++) {
        //         for (int j = 0; j < originalLength; j++) {
        //             newAdjMatrix[i][j] = adjMatrix[i][j];
        //         }
        //     }
        // } else {
        //     newLength = name.length;
        //     newVertices = new Vertex[newLength];
        //     newAdjMatrix = new int[newLength][newLength];
        // }
        // for (int i = 0; i < name.length; i++) {
        //     newVertices[originalLength + i] = new Vertex(name[i]);
        // }
        // vertices = newVertices;
        // adjMatrix = newAdjMatrix;
    }

    private int indexOfVertices(String name) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void insertEdges(String[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int u = indexOfVertices(edges[i][0]);
            int v = indexOfVertices(edges[i][1]);
            adjMatrix[u][v] = 1;
            vertices[v].indegree++;
        }
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void removeOutgoing(String name) {
        int index = indexOfVertices(name);
        for (int i = 0; i < vertices.length; i++) {
            if (adjMatrix[index][i] != 0) {
                adjMatrix[index][i] = 0;
                vertices[i].indegree--;
            }
        }
    }

    public static void findNoIndegreeVert(Queue<Vertex> list, Vertex[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].indegree == 0) {
                list.add(vertices[i]);
                vertices[i].indegree = -1;
            }
        }
    }

    // 4.7
    public static String[] genBuildOrder(String[] projectNames, String[][] projectDependencies) {
        AdjMtxGraph graph = new AdjMtxGraph(projectNames, projectDependencies);
        String[] ordered = new String[projectNames.length];
        int counter = 0;
        Queue<Vertex> noIndegreeVertices = new LinkedList<>();
        findNoIndegreeVert(noIndegreeVertices, graph.getVertices());
        while (!noIndegreeVertices.isEmpty()) {
            String vertName = noIndegreeVertices.remove().name;
            graph.removeOutgoing(vertName);
            ordered[counter++] = vertName;
            findNoIndegreeVert(noIndegreeVertices, graph.getVertices());
        }
        if (counter != projectNames.length) {
            throw new IllegalArgumentException("The projects cannot be ordered.");
        }
        return ordered;
    }

    public static void main(String[] args) {
        String[] projNames = {"a", "b", "c", "d", "e", "f"};
        String[][] projDeps = {{"a", "d"}, {"b", "d"}, {"d", "c"}, {"f", "a"}, {"f", "b"}};
        String[] orderedProjNames = genBuildOrder(projNames, projDeps);
        for (String name : orderedProjNames) {
            System.out.println(name);
        }
    }
}