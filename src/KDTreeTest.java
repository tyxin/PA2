public class KDTreeTest {
    public static void main(String[] args){
        Double[][] list = {{2.0,3.0},{5.0,4.0},{9.0,6.0},{4.0,7.0},{8.0,1.0},{7.0,2.0},{6.0,3.0}};

        KDTree<Double> tree = new KDTree<>(2,list);
        CustomKDTreeNode<Double[]>[] near = tree.findNearest(new Double[]{7.0, 5.0},3);
        for (CustomKDTreeNode<Double[]> i : near) {
            System.out.println(i.getItem()[0] + ", " + i.getItem()[1]);
        }
    }
}
