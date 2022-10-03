public class KDTreeTest {
    public static void main(String[] args){
        Double[][] list = {{2.0,3.0},{5.0,4.0},{9.0,6.0},{4.0,7.0},{8.0,1.0},{7.0,2.0},{6.0,3.0}};
        Facility[] facilityList = new Facility[7];

        for (int i = 0; i < 7; i++) {
            facilityList[i] = new Facility(list[i][0], list[i][1], "MRT", i, i*i);
        }

        KDTree<Double> tree = new KDTree<>(2,list, facilityList);
        CustomKDTreeNode<Double[]>[] near = tree.findNearest(new Double[]{7.0, 5.0},3);
        for (CustomKDTreeNode<Double[]> i : near) {
            System.out.println(i.getItem()[0] + ", " + i.getItem()[1]);
            System.out.println(i.getFacility());
        }
    }
}
