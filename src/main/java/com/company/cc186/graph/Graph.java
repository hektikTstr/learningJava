package com.company.cc186.graph;

import java.util.*;

public class Graph {
    public List<GraphNode> nodes = new ArrayList<>();
    public static class GraphNode {
        public List<GraphNode> children = new ArrayList<>();
        public String name;
        public boolean isVisited;

        public GraphNode(String name) {
            this.name = name;
        }
    }

    public static boolean BFSCheckIfThereIsAWay(GraphNode start, GraphNode end) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            GraphNode node = queue.remove();
            node.isVisited = true;
            if (node == end) {
                return true;
            }
            for (GraphNode n : node.children) {
                if (!n.isVisited) {
                    queue.offer(n);
                }
            }
        }
        return false;
    }

    public static boolean DFSCheckIfThereIsAWay(GraphNode start, GraphNode end) {
        start.isVisited = true;
        if (start == end) {
            return true;
        }
        for (GraphNode node : start.children) {
            if (!node.isVisited && DFSCheckIfThereIsAWay(node, end)) {
                return true;
            }
        }
        return false;
    }

    public void resetNodeVisitRecord() {
        for (GraphNode node : nodes) {
            node.isVisited = false;
        }
    }

    public static void testTopologicalSort() {
        Graph graph = new Graph();
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        graph.nodes.add(a);
        graph.nodes.add(b);
        graph.nodes.add(c);
        graph.nodes.add(d);
        graph.nodes.add(e);
        graph.nodes.add(f);
        a.children.add(d);
        b.children.add(d);
        d.children.add(c);
        f.children.add(a);
        f.children.add(b);
        c.children.add(f);
        List<GraphNode> list = topologicalSort(graph);
    }

    public static List<GraphNode> topologicalSort(Graph graph) {
        Stack<GraphNode> stack = new Stack<>();
        for (GraphNode node : graph.nodes) {
            if (!node.isVisited) {
                topologicalSortRecursive(node, stack);
//                node.isVisited = true;
//                stack.push(node);
            }
        }
        return stack;
    }

    public static void topologicalSortRecursive(GraphNode node, Stack<GraphNode> stack) {
        node.isVisited = true;
        for (GraphNode child : node.children) {
            if (!child.isVisited) {
                topologicalSortRecursive(child, stack);
//                child.isVisited = true;
//                stack.push(child);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        testTopologicalSort();
        Graph graph = new Graph();
        GraphNode a = new GraphNode("A");
        GraphNode b = new GraphNode("B");
        GraphNode c = new GraphNode("C");
        GraphNode d = new GraphNode("D");
        GraphNode e = new GraphNode("E");
        GraphNode f = new GraphNode("F");
        graph.nodes.add(a);
        graph.nodes.add(b);
        graph.nodes.add(c);
        graph.nodes.add(d);
        graph.nodes.add(e);
        graph.nodes.add(f);
        a.children.add(b);
        b.children.add(c);
        b.children.add(d);
        b.children.add(e);
        c.children.add(a);
        d.children.add(c);
        e.children.add(d);
        e.children.add(f);
        System.out.println(BFSCheckIfThereIsAWay(a, b));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(a, c));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(a, d));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(a, e));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(e, a));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(f, a));
        graph.resetNodeVisitRecord();
        System.out.println(BFSCheckIfThereIsAWay(c, d));
        graph.resetNodeVisitRecord();
        System.out.println();
        System.out.println(DFSCheckIfThereIsAWay(a, b));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(a, c));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(a, d));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(a, e));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(e, a));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(f, a));
        graph.resetNodeVisitRecord();
        System.out.println(DFSCheckIfThereIsAWay(c, d));


    }
}
