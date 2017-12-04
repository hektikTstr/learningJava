//package com.company.graph;

import java.util.List;
import java.util.HashMap;

public class VertexImpl<V> implements Vertex<V> {
    private V element;
    private Map<Vertex<V>, Edge<E>> outgoing, incoming;

    public VertexImpl(boolean isDirected) {
        outgoing = new HashMap<Vertex<V>, Edge<E>>;
        incoming = isDirected ? new HashmapVertex<V>, Edge<E> : outgoing;
    } 
    @Override
    public V getElement() {
        return null;
    }

    
}

