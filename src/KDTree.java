public class KDTree<T extends Comparable<? super T>>{
    private int dimensions;
    private Node<T> root;

    public KDTree(int dimensions, T[] nodesList){
        this.dimensions = dimensions;
        constructTree(nodesList);
    }

    private void constructTree(T[] nodesList){
        root = new Node(nodesList[0],2);
    }
}