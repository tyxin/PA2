public class CustomKDTreeNode<T> extends Node<T>{

    public CustomKDTreeNode(T item) {
        super(item);
    }

    public CustomKDTreeNode(T item, int numNeighbours) {
        super(item, numNeighbours);
    }

    public CustomKDTreeNode(T item, Node<T>[] neighbours) {
        super(item, neighbours);
    }

    public CustomKDTreeNode(Node<T> n) {
        super(n);
    }
}
