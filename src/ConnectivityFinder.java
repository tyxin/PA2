import java.util.*;

public class ConnectivityFinder {
    private Map<String, Integer> rankMap ;
    private ArrayList<Location> locationArrayList;

    private ArrayList<KDTree<Facility>> kdTreeArrayList;

    public ConnectivityFinder(Map<String, Integer> rankMap, String locationFilePath){
        this.rankMap = rankMap;
        locationArrayList = importLocationData(locationFilePath);

        createKDTreeList();



    }


    public void createKDTreeList(){
        kdTreeArrayList = new ArrayList<KDTree<Facility>>();
        Iterator<Map.Entry<String, Integer>> itr = rankMap.entrySet().iterator();

        while (itr.hasNext()){
            String type = itr.getKey();
            int rank = itr.getValue();

            KDTree<Facility> kdTree = importFacilityData(type, rank);
            kdTreeArrayList.add(kdTree);

        }

    }


    public void assignConnectivity(){
        for(int i=0;i<locationArrayList.size();i++){
            ArrayList<Facility> facilityArrayList = new ArrayList<>();
            for(int j=0;j<kdTreeArrayList.size();j++){
                int[] arr = kdTreeArrayList.get(j).findNearest(locationArrayList.getCoords(),4);
                for(int k=0;k<arr.length;k++){
                    facilityArrayList.add(arr[[k]]);
                }
            }
            locationArrayList.get(i).setConnectivity(facilityArrayList);
        }
    }




    public static KDTree<Facility> importFacilityData(String facilityType, int rank) {
        ArrayList<Facility> facilities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/resources/updated/"+facilityType+"_locations.csv"));
            while(scanner.hasNext()){
                facilities.add(new Facility(scanner.nextLine(), facilityType, rank));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[][] coords = new double[facilities.size()][2];
        for (int i = 0; i < facilities.size(); i++) coords[i] = facilities.get(i).getCoords();
        return new KDTree<Facility>(2, facilities.toArray(new Facility[0]), coords);
    }



    public static ArrayList<Location> importLocationData(String filename) {
        ArrayList<Location> locations = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNext()){
                locations.add(new Location(scanner.nextLine()));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return locations;
    }


}
