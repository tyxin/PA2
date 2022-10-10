package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConnectivityFinder {

    ArrayList<String> facilityTypeList, filePathList;
    ArrayList<Integer> rankList;
    private ArrayList<Location> estateArrayList;

    // completely arbitrary constant that decides how many closest estates to factor in
    // when we decide a Model.Location's potential connectivity improvement
    private final int N_ESTATES = 4;

    private ArrayList<KDTree<Facility>> kdTreeArrayList;
    private KDTree<Location> estatesKdTree;

    public ConnectivityFinder(ArrayList<String> facilityTypeList, ArrayList<Integer> rankList, ArrayList<String> filePathList, String locationFilePath) throws FileNotFoundException{
        estateArrayList = importEstateData(locationFilePath);

        this.facilityTypeList = facilityTypeList;
        this.filePathList = filePathList;
        this.rankList = rankList;

        createKDTreeList();

    }


    public void createKDTreeList(){
        kdTreeArrayList = new ArrayList<KDTree<Facility>>();

        for (int i=0;i<filePathList.size();i++) {

            KDTree<Facility> kdTree = importFacilityData(filePathList.get(i), facilityTypeList.get(i), rankList.get(i));
            kdTreeArrayList.add(kdTree);

        }

        double[][] coords = new double[estateArrayList.size()][2];
        for (int i = 0; i < estateArrayList.size(); i++) coords[i] = estateArrayList.get(i).getCoords();
        estatesKdTree = new KDTree<>(2, estateArrayList.toArray(new Location[0]), coords);

    }

    // after initialising the locations and kdtrees, can be
    // called to assign connectivity values for each location
    public void assignConnectivity(){
        for(int i = 0; i< estateArrayList.size(); i++){
            ArrayList<Facility> facilityArrayList = new ArrayList<>();
            for(int j=0;j<kdTreeArrayList.size();j++){
                CustomKDTreeNode<Facility>[] arr = kdTreeArrayList.get(j).findNearest(estateArrayList.get(i).getCoords(),4);
                for(int k=0;k<arr.length;k++){
                    facilityArrayList.add(arr[k].getItem());
                }
            }
            estateArrayList.get(i).setConnectivity(facilityArrayList);
        }
    }

    // given a possible location for a hub to be built, finds the N_ESTATES
    // closest estates to that location and then calculates how "connected"
    // that location already is
    public void calculatePotentialHubImprovement(HubLocation possibleHub) {
        CustomKDTreeNode<Location>[] nearestEstateNodes = estatesKdTree.findNearest(possibleHub.getCoords(), N_ESTATES);
        ArrayList<Location> nearestEstates = new ArrayList<>();
        for (CustomKDTreeNode<Location> node: nearestEstateNodes) {nearestEstates.add(node.getItem()); }
        possibleHub.setImprovement(nearestEstates);
    }

    public ArrayList<HubLocation> sortHubLocations(String hubsFilePath) throws FileNotFoundException{

        ArrayList<HubLocation> locations = importHubLocations(hubsFilePath);
        for (HubLocation possibleHub: locations) {
            calculatePotentialHubImprovement(possibleHub);
        }

        locations.sort(Collections.reverseOrder(Comparator.comparingDouble(HubLocation::getImprovement)));
        return locations;
    }




    public static KDTree<Facility> importFacilityData(String filePath, String facilityType, int rank) {
        ArrayList<Facility> facilities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
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



    public static ArrayList<Location> importEstateData(String filename) throws FileNotFoundException{
        ArrayList<Location> locations = new ArrayList<>();

        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNext()){
            locations.add(new Location(scanner.nextLine()));
        }
        scanner.close();

        return locations;
    }

    public static ArrayList<HubLocation> importHubLocations(String filename) throws FileNotFoundException{
        ArrayList<HubLocation> locations = new ArrayList<>();

        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNext()){
            locations.add(new HubLocation(scanner.nextLine()));
        }
        scanner.close();


        return locations;
    }


}
