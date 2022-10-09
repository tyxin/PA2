import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataTest {
    public static void main(String[] args) {
        KDTree<Facility> tree = importFacilityData("src/resources/updated/prisch_locations.csv");

        Location[] estates = importLocationData("src/resources/estate_locations.csv");
        Location chosenLocation = estates[3];
        System.out.println(chosenLocation);

        CustomKDTreeNode<Facility>[] near = tree.findNearest(chosenLocation.getCoords(),5);
        for (CustomKDTreeNode<Facility> i : near) {
            System.out.println(i.getItem());
        }

    }

    // to be used for facility datasets i.e. those with a trailing number indicating FacilityType
    public static KDTree<Facility> importFacilityData(String filename) {
        ArrayList<Facility> facilities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNext()){
                facilities.add(new Facility(scanner.nextLine()));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[][] coords = new double[facilities.size()][2];
        for (int i = 0; i < facilities.size(); i++) coords[i] = facilities.get(i).getCoords();
        return new KDTree<Facility>(2, facilities.toArray(new Facility[0]), coords);
    }

    // for other coords
    public static Location[] importLocationData(String filename) {
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

        return locations.toArray(new Location[0]);
    }
}
