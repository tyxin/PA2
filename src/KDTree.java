public class KDTree<T extends Comparable<? super T>>{
    private int dimensions;
    private Node<T[]> root;

    public KDTree(int dimensions, T[][] nodesList){
        this.dimensions = dimensions;
        constructTree(nodesList);
    }

    private void constructTree(T[][] nodesList){
        for(T[] n: nodesList) {
            root = insert(root,n);
        }
    }
    public Node<T[]> insert(Node<T[]> root, T[] coord) {
        return insertHelper(root, coord, 0);
    }
    public Node<T[]> insertHelper(Node<T[]> root, T[] coord, int depth) {
        if(root == null) return new Node(coord,2);
        int cd = depth % dimensions;
        if (coord[cd].compareTo(root.getItem()[cd]) < 0)
            root.neighbours[0] = insertHelper(root.neighbours[0], coord, depth + 1);
        else
            root.neighbours[1] = insertHelper(root.neighbours[1], coord, depth + 1);
        return root;
    }
}