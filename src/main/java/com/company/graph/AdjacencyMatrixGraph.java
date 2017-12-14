package com.company.graph;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
//import java.lang.IllegalArgumentException;

public class AdjacencyMatrixGraph<V, E> implements Graph<V, E> {
    private class VertexImpl<V> implements Vertex<V> {
        private V element;

        public VertexImpl(V element) {
            this.element = element;
        }
        public V getElement() {
            return element;
        }
        public boolean validate(Graph<V,E> graph) {
            return (AdjacencyMatrixGraph.this == graph);
        }   
    }

    private class EdgeImpl<E> implements Edge<E> {
        private E element;
        private Vertex<V>[] endpoints;
        @SuppressWarnings({"unchecked"})
        public EdgeImpl(Vertex<V> u, Vertex<V> v, E elem) {
            endpoints = new Vertex[]{u, v};
            element = elem;
        }
        public E getElement() {
            return element;
        }
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        public boolean validate(Graph<V, E> graph) {
            return (AdjacencyMatrixGraph.this == graph);
        }
    }

    private boolean isDirected;
    private List<Vertex<V>> vertices = new LinkedList<>();
    private List<Edge<E>> edges = new LinkedList<>();
    private Edge<E>[][] matrix;

    public AdjacencyMatrixGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }
    @SuppressWarnings({"unchecked"})
    private VertexImpl<V> validate(Vertex<V> vertex) throws IllegalArgumentException {
        if (!(vertex instanceof VertexImpl)) throw new IllegalArgumentException("Invalid vertex");
        VertexImpl<V> vert = (VertexImpl<V>) vertex;
        if (!vert.validate(this)) throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }
    @SuppressWarnings({"unchecked"})
    private EdgeImpl<E> validate(Edge<E> e) throws IllegalArgumentException {
        if (!(e instanceof EdgeImpl)) throw new IllegalArgumentException("Invalid edge");
        EdgeImpl<E> edge = (EdgeImpl<E>) e;
        if (!edge.validate(this)) throw new IllegalArgumentException("Invalid edge");
        return edge;
    }
    public int numVertices() {
        return vertices.size();
    }
    public int numEdges() {
        return edges.size();
    }
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }
    public Iterable<Edge<E>> edges() {
        return edges;
    }
    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert = validate(v);
        int counter = 0;
        int index = vertices.indexOf(v);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[index][i] != null) {
                counter++; 
            }
        }
        return counter;
    }
    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert = validate(v);
        int counter = 0;
        int index = vertices.indexOf(v);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][index] != null) {
                counter++; 
            }
        }
        return counter;
    }
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert = validate(v);
        List<Edge<E>> list = new ArrayList<>();
        int index = vertices.indexOf(v);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[index][i] != null) {
                list.add(matrix[index][i]);
            }
        }
        return list;
    }
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert = validate(v);
        List<Edge<E>> list = new ArrayList<>();
        int index = vertices.indexOf(v);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][index] != null) {
                list.add(matrix[i][index]);
            }
        }
        return list;
    }
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vertU = validate(u);
        VertexImpl<V> vertV = validate(v);
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU == -1 || indexV == -1) {
            return null;
        }
        return matrix[indexU][indexV];
    }
    @SuppressWarnings({"unchecked"})
    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        EdgeImpl<E> edge = (EdgeImpl<E>) e;
        return edge.getEndpoints();
    }
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        Vertex<V>[] endV = endVertices(e);
        if (v == endV[0]) {
            return endV[1];
        } else if (v == endV[1]) {
            return endV[0];
        } else {
            throw new IllegalArgumentException("Vertex is not indent to this edge");
        }
    }
    @SuppressWarnings({"unchecked"})
    public Vertex<V> insertVertex(V element) {
        VertexImpl<V> vert = new VertexImpl(element);
        vertices.add(vert);
        int vertexNumber = vertices.size();
        Edge<E>[][] newMatrix = new Edge[vertexNumber][vertexNumber];
        for (int i = 0; i < vertexNumber - 1; i++) {
            for (int j = 0; j < vertexNumber - 1; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        return vert;
    }
    @SuppressWarnings({"unchecked"})
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            EdgeImpl<E> edge = new EdgeImpl(u, v, element);
            edges.add(edge);
            VertexImpl<V> startVertex = validate(u);
            VertexImpl<V> endVertex = validate(v);
            matrix[vertices.indexOf(startVertex)][vertices.indexOf(endVertex)] = edge;
            return edge;
        } else {
            throw new IllegalArgumentException("Edge from u to v already exist");
        }
    }
    @SuppressWarnings({"unchecked"})
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert = validate(v);
        int vertexNumber = vertices.size() - 1;
        if (vertexNumber >= 2) {
            int index = vertices.indexOf(vert);
            Edge<E>[][] newMatrix = new Edge[vertexNumber][vertexNumber];
            int row = 0;
            int column = 0;
            for (int i = 0; i < vertexNumber; i++) {
                row = i < index ? i : i + 1;
                for (int j = 0; j < vertexNumber; j++) {
                    column = j < index ? j : j + 1;
                    newMatrix[i][j] = matrix[row][column];
                }
            }
            matrix = newMatrix;
        } else {
            matrix = null;
        }
        vertices.remove(vert);
    }
    @SuppressWarnings({"unchecked"})
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        EdgeImpl<E> edge = validate(e);
        edges.remove(edge);
        Vertex<V>[] endpoints = edge.getEndpoints();
        matrix[vertices.indexOf(endpoints[0])][vertices.indexOf(endpoints[0])] = null;
    }

    public static void main(String[] args) {
        Graph<String, Integer> undirectGraph = new AdjacencyMatrixGraph<>(true);
        Vertex<String> vA = undirectGraph.insertVertex("A");
        Vertex<String> vB = undirectGraph.insertVertex("B");
        Vertex<String> vC = undirectGraph.insertVertex("C");
        Vertex<String> vD = undirectGraph.insertVertex("D");
        undirectGraph.insertEdge(vB, vA, 1);
        undirectGraph.insertEdge(vC, vA, 5);
        undirectGraph.insertEdge(vD, vB, 2);
        undirectGraph.insertEdge(vC, vB, 4);
        undirectGraph.insertEdge(vD, vC, 3);
        for (Vertex<String> v : undirectGraph.vertices()) {
            System.out.println("Vertex elem = " + v.getElement());
            System.out.println("Outdegree num = " + undirectGraph.outDegree(v));
            System.out.println("Indegree num = " + undirectGraph.inDegree(v));
            for (Edge<Integer> e : undirectGraph.outgoingEdges(v)) {
                System.out.println("  edge = " + e.getElement());
            }
        }
        for (Edge<Integer> e : undirectGraph.edges()) {
            System.out.println("Edge elem = " + e.getElement());
        }
        System.out.println("Vertex num = " + undirectGraph.numVertices());
        System.out.println("Edge num = " + undirectGraph.numEdges());
        Graph<String, Integer> directGraph = new AdjacencyMatrixGraph<>(true);
    }
}
