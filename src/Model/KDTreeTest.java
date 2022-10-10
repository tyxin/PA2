package Model;
public class KDTreeTest {
    public static void main(String[] args){
        double[][] list = {{2.0,3.0},{5.0,4.0},{9.0,6.0},{4.0,7.0},{8.0,1.0},{7.0,2.0},{6.0,3.0}};
        Facility[] facilityList = new Facility[7];

        for (int i = 0; i < 7; i++) {
            facilityList[i] = new Facility(list[i][0], list[i][1], "MRT", i, i*i);
        }

        KDTree<Facility> tree = new KDTree<>(2, facilityList, list);
        CustomKDTreeNode<Facility>[] near = tree.findNearest(new double[]{7.0, 5.0},3);
        for (CustomKDTreeNode<Facility> i : near) {
            System.out.println(i.getItem());
        }
        System.out.println();
        System.out.println(tree.search(new double[]{8.0,1.0}));
        System.out.println(tree.search(new double[]{1.0,1.0}));
        tree.insert(null, new double[]{1.0,1.0});
        System.out.println(tree.search(new double[]{8.0,1.0}));
        System.out.println(tree.search(new double[]{1.0,1.0}));
        tree.delete(new double[]{8.0,1.0});
        System.out.println(tree.search(new double[]{8.0,1.0}));
        System.out.println(tree.search(new double[]{1.0,1.0}));
    }
}
