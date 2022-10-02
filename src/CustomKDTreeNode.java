public class CustomKDTreeNode<T> extends Node<T>{

    private double dist;
    CustomKDTreeNode[] customNeighbours;
    public CustomKDTreeNode(T item) {
        super(item);
    }

    public CustomKDTreeNode(T item, int numNeighbours) {
        super(item);
        this.customNeighbours = new CustomKDTreeNode[numNeighbours];
    }

    public CustomKDTreeNode(T item, CustomKDTreeNode<T>[] neighbours) {
        super(item);
        this.customNeighbours = new CustomKDTreeNode[neighbours.length];
        for (int i=0; i<neighbours.length; i++)
            this.customNeighbours[i] = new CustomKDTreeNode(neighbours[i]);
    }
    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
