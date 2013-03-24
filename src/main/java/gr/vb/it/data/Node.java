package gr.vb.it.data;


import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

public class Node<T> {

    protected List<Node<T>> children = new ArrayList<Node<T>>();
    protected Node<T> parent;
    protected T data;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Deprecated
    public Node<T> getNode(T data) {
        if (data != null && data.equals(data)) {
            return this;
        } else {
            return walkIn(this, data);
        }
    }

    public Node<T> findNode(Predicate<Node<T>> predicate) {
        return walkin(this, predicate);
    }
    
    public List<Node<T>> findNodes(Predicate<Node<T>> predicate) {
        return walkins(this, new ArrayList<Node<T>>(), predicate);
    }

    private Node<T> walkin(Node<T> node, Predicate<Node<T>> predicate) {
        if (predicate.apply(node)) {
            return node;
        } else {
            for (Node<T> n : node.getChildren()) {
                Node<T> result = walkin(n, predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    
    private List<Node<T>> walkins(Node<T> node, List<Node<T>> list, Predicate<Node<T>> predicate) {
        if (predicate.apply(node)) {
            list.add(node);
        }
        for (Node<T> n : node.getChildren()) {
            walkins(n, list, predicate);
        }
        return list;
    }

    @Deprecated
    private Node<T> walkIn(Node<T> node, T data) {
        for (Node<T> n : node.getChildren()) {
            if (n.equals(node.getData())) {
                return n;
            } else {
                return walkIn(n, data);
            }
        }
        return null;
    }

    /**
     * Override the equals method so that equality is determined by the node's
     * data.
     *
     * @param obj
     * @return true if this.data equals obj.data
     */
    @Override
    public boolean equals(Object obj) {
        try {
            @SuppressWarnings("unchecked")
            Node<T> n = (Node<T>) obj;
            return data.equals(n.getData());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.children != null ? this.children.hashCode() : 0);
        hash = 79 * hash + (this.parent != null ? this.parent.hashCode() : 0);
        hash = 79 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    /**
     * Add a node as a child of this node.
     *
     * @param child
     * @return
     */
    public Node<T> addChild(Node<T> child) throws NodeExistsException {
        child.setParent(this);
        children.add(child);
        return this;
    }

    public int getNodeDepth() {
        int depth = 0;
        Node<T> parentNode = this.parent;
        while (parentNode != null) {
            depth++;
            parentNode = parentNode.getParent();
        }
        return depth;
    }

    /**
     * Return node's children
     *
     * @return
     */
    public List<Node<T>> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getData());
        sb.append("\n");
        int depthCounter = 0;
        printToString(this, sb, depthCounter);
        return sb.toString();
    }

    private void printToString(Node<T> node, StringBuilder sb, int depthCounter) {
        depthCounter++;
        for (Node<T> n : node.getChildren()) {
            for (int i = 0; i < depthCounter; i++) {
                sb.append("\t");
            }
            sb.append("Child : ").append(depthCounter).append(" ");
            sb.append(n.getData());
            sb.append("\n");
            if (n.getChildren().size() > 0) {
                printToString(n, sb, depthCounter);
                sb.append("\n");
            }
        }
    }

    public static class NodeExistsException extends RuntimeException {

        private static final long serialVersionUID = 1L;
        private String msg = "There is already a node with the same data under this node.";

        @Override
        public String getMessage() {
            return msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }


}
