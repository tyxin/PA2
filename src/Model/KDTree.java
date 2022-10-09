package Model;

import java.util.*;

public class KDTree<T> {
    private int dimensions;
    private CustomKDTreeNode<T> root;

    private double minDist = Double.MAX_VALUE;
    private PriorityQueue<CustomKDTreeNode<T>> nearestNeighbours = new PriorityQueue<>(
            10, (n1, n2) -> {
                if (n1.getDist() < n2.getDist()) return 1;
                if (n1.getDist() > n2.getDist()) return -1;
                return 0;
            });

    public KDTree(int dimensions, double[][] points) {
        this.dimensions = dimensions;
        ArrayList<CustomKDTreeNode<T>> nodes = new ArrayList<>(points.length);
        for(int i = 0; i < points.length; i++) {
            nodes.add(new CustomKDTreeNode<>(null,2,points[i]));
        }
        root = construct(null, nodes, 0);
    }
    public KDTree(int dimensions, T[] facilities, double[][] points) {
        this.dimensions = dimensions;
        ArrayList<CustomKDTreeNode<T>> nodes = new ArrayList<>(points.length);
        for(int i = 0; i < points.length; i++) {
            nodes.add(new CustomKDTreeNode<>(facilities[i],2,points[i]));
        }
        root = construct(null, nodes, 0);
    }

    private CustomKDTreeNode<T> construct(CustomKDTreeNode<T> parent, ArrayList<CustomKDTreeNode<T>> nodes, int depth) {
        if (nodes.isEmpty()) {
            return null;
        }
        int cd = depth % dimensions;
        CustomKDTreeNode<T> medianNode = getApproxMedian(nodes, cd);
        ArrayList<CustomKDTreeNode<T>> leftOfMedian = new ArrayList<>(nodes.size() / 2);
        ArrayList<CustomKDTreeNode<T>> rightOfMedian = new ArrayList<>(nodes.size() / 2);

        for (CustomKDTreeNode<T> node : nodes) {
            if (node.equals(medianNode)) {
                continue;
            }
            if (node.getPoint()[cd] < medianNode.getPoint()[cd]) leftOfMedian.add(node);
            else rightOfMedian.add(node);
        }
        medianNode.customNeighbours[0] = construct(medianNode, leftOfMedian, depth + 1);
        medianNode.customNeighbours[1] = construct(medianNode, rightOfMedian, depth + 1);
        return medianNode;
    }


    private CustomKDTreeNode<T> getApproxMedian(ArrayList<CustomKDTreeNode<T>> nodes, int cd) {
        int numberOfElements = (int) Math.max(nodes.size() * 0.1, 1);
        // pick a random subset from the list
        ArrayList<CustomKDTreeNode<T>> subset = pickRandomSubset(nodes, numberOfElements);
        // get the median of that subset, assume it is close enough to actual median
        subset.sort(Comparator.comparingDouble(point -> point.getPoint()[cd]));
        return subset.get(subset.size() / 2);
    }

    private ArrayList<CustomKDTreeNode<T>> pickRandomSubset(ArrayList<CustomKDTreeNode<T>> nodes, int numberOfElements) {
        ArrayList<CustomKDTreeNode<T>> subset = new ArrayList<>(numberOfElements);
        Random random = new Random(42);
        for (int i = 0; i < numberOfElements; i++) {
            int index = random.nextInt(numberOfElements);
            subset.add(nodes.get(index));
        }
        return subset;
    }

    public CustomKDTreeNode<T> getRoot(){return root;}

    public double getDistanceSquared(double[] point1, double[] point2) {
        double distance = 0;
        for (int i = 0; i < dimensions; i++) {
            double delta = point1[i] - point2[i];
            distance += (delta * delta);
        }
        return distance;
    }

    public CustomKDTreeNode<T>[] findNearest(double[] target, int k){
        if (root==null){
            throw new NoSuchElementException("KD Tree is empty!");
        }
        nearest(root, new CustomKDTreeNode<>(null,target), 0, k);
        return nearestNeighbours.toArray(new CustomKDTreeNode[0]);
    }

    private void nearest(CustomKDTreeNode<T> node, CustomKDTreeNode<T> nodeTarget, int depth, int k) {
        if(node==null) return;
        double dist = getDistanceSquared(node.getPoint(), nodeTarget.getPoint());
        node.setDist(dist);
        if (dist < minDist && dist != 0) {
            minDist = dist;
        }
        nearestNeighbours.add(node);
        if (nearestNeighbours.size() > k) nearestNeighbours.poll();
        int cd = depth % dimensions;
        if (nodeTarget.getPoint()[cd] < node.getPoint()[cd]) {
            nearest(node.customNeighbours[0], nodeTarget, depth + 1, k);
            if (nodeTarget.getPoint()[cd] + minDist >= node.getPoint()[cd])
                nearest(node.customNeighbours[1], nodeTarget, depth + 1, k );
        } else {
            nearest(node.customNeighbours[1], nodeTarget, depth + 1,k );
            if (nodeTarget.getPoint()[cd] - minDist <= node.getPoint()[cd])
                nearest(node.customNeighbours[0], nodeTarget, depth + 1, k);
        }
    }
}
