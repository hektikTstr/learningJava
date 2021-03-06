import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
//import java.lang.IllegalArgumentException;

public class EdgeListGraph<V, E> implements Graph<V, E> {
    private class VertexImpl<V> implements Vertex<V> {
        private V element;

        public VertexImpl(V element) {
            this.element = element;
        }
        public V getElement() {
            return element;
        }
        public boolean validate(Graph<V,E> graph) {
            return (EdgeListGraph.this == graph);
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
            return (EdgeListGraph.this == graph);
        }
    }

    private boolean isDirected;
    private List<Vertex<V>> vertices = new LinkedList<>();
    private List<Edge<E>> edges = new LinkedList<>();

    public EdgeListGraph(boolean isDirected) {
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
    @SuppressWarnings({"unchecked"})
    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        int counter = 0;
        for (Edge<E> edge : edges) {
            if (((EdgeImpl<E>) edge).getEndpoints()[0] == v) counter++;
        }
        return counter;
    }
    @SuppressWarnings({"unchecked"})
    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        int counter = 0;
        for (Edge<E> edge : edges) {
            if (((EdgeImpl<E>) edge).getEndpoints()[1] == v) counter++;
        }
        return counter;
    }
    @SuppressWarnings({"unchecked"})
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        List<Edge<E>> iterable = new ArrayList<>();
        for (Edge<E> edge : edges) {
            if (((EdgeImpl<E>) edge).getEndpoints()[0] == v) iterable.add(edge);
        }
        return iterable;
    }
    @SuppressWarnings({"unchecked"})
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        List<Edge<E>> iterable = new ArrayList<>();
        for (Edge<E> edge : edges) {
            if (((EdgeImpl<E>) edge).getEndpoints()[1] == v) iterable.add(edge);
        }
        return iterable;
    }
    @SuppressWarnings({"unchecked"})
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        for (Edge<E> edge : edges) {
            if (((EdgeImpl<E>) edge).getEndpoints()[0] == u
                && ((EdgeImpl<E>) edge).getEndpoints()[1] == v) return edge;
        }
        return null;
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
        return vert;
    }
    @SuppressWarnings({"unchecked"})
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            EdgeImpl<E> edge = new EdgeImpl(u, v, element);
            edges.add(edge);
            return edge;
        } else {
            throw new IllegalArgumentException("Edge from u to v already exist");
        }
    }
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        VertexImpl<V> vert= validate(v);
        Iterator<Edge<E>> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge<E> edge = iterator.next();
            if (endVertices(edge)[0] == v
                || endVertices(edge)[1] == v) iterator.remove();;
        }
        vertices.remove(vert);
    }
    @SuppressWarnings({"unchecked"})
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        EdgeImpl<E> edge = validate(e);
        edges.remove(edge);
    }

    public static void main(String[] args) {
        Graph<String, Integer> undirectGraph = new EdgeListGraph<>(false);
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
        Graph<String, Integer> directGraph = new EdgeListGraph<>(true);
    }
}
