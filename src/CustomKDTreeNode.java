public class CustomKDTreeNode<T> extends Node<T>{

    private CustomKDTreeNode<T> parent;
    private int depth;
    private int axis;
    CustomKDTreeNode[] customNeighbours;
    public CustomKDTreeNode(T item) {
        super(item);
    }

    public CustomKDTreeNode(T item, int numNeighbours) {
        super(item);
        this.customNeighbours = new CustomKDTreeNode[numNeighbours];
    }
    public CustomKDTreeNode(T item, int numNeighbours, int depth, int axis) {
        super(item);
        this.customNeighbours = new CustomKDTreeNode[numNeighbours];
        this.depth = depth;
        this.axis = axis;
    }

    public CustomKDTreeNode(T item, CustomKDTreeNode<T>[] neighbours) {
        super(item);
        this.customNeighbours = new CustomKDTreeNode[neighbours.length];
        for (int i=0; i<neighbours.length; i++)
            this.customNeighbours[i] = new CustomKDTreeNode(neighbours[i]);
    }
    public int getDepth() {
        return depth;
    }

    public int getAxis() {
        return axis;
    }
}
