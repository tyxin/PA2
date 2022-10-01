import java.util.NoSuchElementException;

public class KDTree<T extends Comparable<? super T>> {
    private int dimensions;
    private CustomKDTreeNode<T[]> root;

    public KDTree(int dimensions, T[][] nodesList) {
        this.dimensions = dimensions;
        constructTree(nodesList);
    }

    private void constructTree(T[][] nodesList) {
        for (T[] n : nodesList) {
            root = insert(root, n);
        }
    }

    public CustomKDTreeNode<T[]> insert(CustomKDTreeNode<T[]> root, T[] point) {
        return insertHelper(root, point, 0);
    }

    public CustomKDTreeNode<T[]> insertHelper(CustomKDTreeNode<T[]> root, T[] point, int depth) {
        if (root == null) return new CustomKDTreeNode<>(point, 2);
        int cd = depth % dimensions;
        if (point[cd].compareTo(root.getItem()[cd]) < 0)
            root.neighbours[0] = insertHelper(root.customNeighbours[0], point, depth + 1);
        else
            root.neighbours[1] = insertHelper(root.customNeighbours[1], point, depth + 1);
        return root;
    }

    public boolean areSame(T[] point1, T[] point2) {
        for (int i = 0; i < dimensions; ++i)
            if (point1[i] != point2[i])
                return false;
        return true;
    }
    public boolean search (CustomKDTreeNode<T[]> root, T[] point) {
        return searchHelper(root, point, 0);
    }

    public boolean searchHelper (CustomKDTreeNode<T[]> root, T[] point, int depth) {
        if (root == null) return false;
        if (areSame(root.getItem(), point))
            return true;

        int cd = depth % dimensions;

        if (point[cd].compareTo(root.getItem()[cd]) < 0)
            return searchHelper(root.customNeighbours[0], point, depth + 1);

        return searchHelper(root.customNeighbours[1], point, depth + 1);
    }

    public CustomKDTreeNode<T> findNearest(CustomKDTreeNode<T> nodeTarget){
        //TODO
        if (root==null){
            throw new NoSuchElementException("KD Tree is empty!");
        }
        return null;
    }
}
