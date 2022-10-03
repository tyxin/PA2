public class CustomKDTreeNode<T> extends Node<T>{

    private double dist;
    private Facility facility;
    CustomKDTreeNode[] customNeighbours;

    public CustomKDTreeNode(T item) {
        super(item);
    }

    public CustomKDTreeNode(T item, int numNeighbours, Facility facility) {
        super(item);
        this.facility = facility;
        this.customNeighbours = new CustomKDTreeNode[numNeighbours];
    }

    public CustomKDTreeNode(T item, CustomKDTreeNode<T>[] neighbours, Facility facility) {
        super(item);
        this.facility = facility;
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

    public Facility getFacility() {
        return facility;
    }
    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public void setCustomNeighbour(int position, CustomKDTreeNode<T> newNeighbour) {
        this.customNeighbours[position] = newNeighbour;
    }

}
