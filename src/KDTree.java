public class KDTree<T extends Comparable<? super T>> {
    private int dimensions;
    private Node<T[]> root;

    public KDTree(int dimensions, T[][] nodesList) {
        this.dimensions = dimensions;
        constructTree(nodesList);
    }

    private void constructTree(T[][] nodesList) {
        for (T[] n : nodesList) {
            root = insert(root, n);
        }
    }

    public Node<T[]> insert(Node<T[]> root, T[] point) {
        return insertHelper(root, point, 0);
    }

    public Node<T[]> insertHelper(Node<T[]> root, T[] point, int depth) {
        if (root == null) return new Node(point, 2);
        int cd = depth % dimensions;
        if (point[cd].compareTo(root.getItem()[cd]) < 0)
            root.neighbours[0] = insertHelper(root.neighbours[0], point, depth + 1);
        else
            root.neighbours[1] = insertHelper(root.neighbours[1], point, depth + 1);
        return root;
    }

    public boolean areSame(T[] point1, T[] point2) {
        for (int i = 0; i < dimensions; ++i)
            if (point1[i] != point2[i])
                return false;
        return true;
    }
    public boolean search (Node<T[]> root, T[] point) {
        return searchHelper(root, point, 0);
    }
    public boolean searchHelper (Node<T[]> root, T[] point, int depth) {
        if (root == null) return false;
        if (areSame(root.getItem(), point))
            return true;

        int cd = depth % dimensions;

        if (point[cd].compareTo(root.getItem()[cd]) < 0)
            return searchHelper(root.neighbours[0], point, depth + 1);

        return searchHelper(root.neighbours[1], point, depth + 1);
    }
}
