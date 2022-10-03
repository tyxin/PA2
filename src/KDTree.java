import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
public class KDTree<T extends Number & Comparable<? super T>> {
    private int dimensions;
    private CustomKDTreeNode<T[]> root;

    private double minDist = Double.MAX_VALUE;
    private PriorityQueue<CustomKDTreeNode<T[]>> nearestNeighbours =new PriorityQueue<CustomKDTreeNode<T[]>>(
            10, new Comparator<CustomKDTreeNode<T[]>>() {
        public int compare(CustomKDTreeNode<T[]> n1, CustomKDTreeNode<T[]> n2) {
            if (n1.getDist() < n2.getDist()) return 1;
            if (n1.getDist() > n2.getDist()) return -1;
            return 0;
        }
    });
    public KDTree(int dimensions, T[][] nodesList, Facility[] facilities) {
        this.dimensions = dimensions;
        for (int i = 0; i < nodesList.length; i++) {
            root = insert(root, nodesList[i], facilities[i]);
        }
    }
    public KDTree(int dimensions, Facility[] facilities) {
        this.dimensions = dimensions;
        for (int i = 0; i < facilities.length; i++) {
            root = insert(root, (T[]) facilities[i].getCoords(), facilities[i]);
        }
    }
    public CustomKDTreeNode<T[]> getRoot(){return root;}

    public CustomKDTreeNode<T[]> insert(CustomKDTreeNode<T[]> parent, T[] point, Facility facility) {
        return insertHelper(parent, point, facility, 0);
    }
    public CustomKDTreeNode<T[]> insertHelper(CustomKDTreeNode<T[]> parent, T[] point, Facility facility, int depth) {
        int cd = depth % dimensions;
        if (parent == null) return new CustomKDTreeNode<>(point, 2, facility);

        if (point[cd].compareTo(parent.getItem()[cd]) < 0)
            parent.setCustomNeighbour(0, insertHelper(parent.customNeighbours[0], point, facility, depth + 1));
        else
            parent.setCustomNeighbour(1, insertHelper(parent.customNeighbours[1], point, facility, depth + 1));
        return parent;
    }

    public double getDistanceSquared(T[] point1, T[] point2) {
        double distance = 0;
        for (int i = 0; i < dimensions; i++) {
            double delta = point1[i].doubleValue() - point2[i].doubleValue();
            distance += (delta * delta);
        }
        return distance;
    }

    public CustomKDTreeNode<T[]>[] findNearest(T[] target, int k){
        if (root==null){
            throw new NoSuchElementException("KD Tree is empty!");
        }
        nearest(root, new CustomKDTreeNode<>(target), 0, k);
        return nearestNeighbours.toArray(new CustomKDTreeNode[0]);
    }

    private void nearest(CustomKDTreeNode<T[]> node, CustomKDTreeNode<T[]> nodeTarget, int depth, int k) {
        if(node==null) return;
        double dist = getDistanceSquared(node.getItem(), nodeTarget.getItem());
        node.setDist(dist);
        if (dist < minDist && dist != 0) {
            minDist = dist;
        }
        nearestNeighbours.add(node);
        if (nearestNeighbours.size() > k) nearestNeighbours.poll();
        int cd = depth % dimensions;
        if (nodeTarget.getItem()[cd].compareTo(node.getItem()[cd]) < 0) {
            nearest(node.customNeighbours[0], nodeTarget, depth + 1, k);
            if (nodeTarget.getItem()[cd].doubleValue() + minDist >= node.getItem()[cd].doubleValue())
                nearest(node.customNeighbours[1], nodeTarget, depth + 1,k );
        } else {
            nearest(node.customNeighbours[1], nodeTarget, depth + 1,k );
            if (nodeTarget.getItem()[cd].doubleValue() - minDist <= node.getItem()[cd].doubleValue())
                nearest(node.customNeighbours[0], nodeTarget, depth + 1, k);
        }
    }
}
