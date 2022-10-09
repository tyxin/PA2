package Model;

import java.util.Arrays;

public class CustomKDTreeNode<T> extends Node<T>{
    private double dist;
    private double[] point;
    CustomKDTreeNode<T>[] customNeighbours;

    public CustomKDTreeNode(T item, double[] point) {
        super(item);
        this.point = point;
    }

    public CustomKDTreeNode(T item, int numNeighbours, double[] point) {
        super(item);
        this.point = point;
        this.customNeighbours = new CustomKDTreeNode[numNeighbours];
    }

    public CustomKDTreeNode(T item, CustomKDTreeNode<T>[] neighbours, double[] point) {
        super(item);
        this.point = point;
        this.customNeighbours = new CustomKDTreeNode[neighbours.length];
        for (int i=0; i<neighbours.length; i++)
            this.customNeighbours[i] = neighbours[i];
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public double[] getPoint() {
        return point;
    }
    public void setPoint(double[] point) {
        this.point = point;
    }

    public void setCustomNeighbour(int position, CustomKDTreeNode<T> newNeighbour) {
        this.customNeighbours[position] = newNeighbour;
    }

    public boolean equals(CustomKDTreeNode node) {
        return Arrays.equals(this.point,node.point);
    }
}
