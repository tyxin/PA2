import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataTest {
    public static void main(String[] args) {
        Facility[] prischs = importFacilityData("src/resources/prisch_locations.csv");
        KDTree<Double> tree = new KDTree<>(2, prischs);

        Location[] estates = importLocationData("src/resources/estate_locations.csv");
        Location chosenLocation = estates[3];
        System.out.println(chosenLocation);

        CustomKDTreeNode<Double[]>[] near = tree.findNearest(chosenLocation.getCoords(),5);
        for (CustomKDTreeNode<Double[]> i : near) {
            System.out.println(i.getItem()[0] + ", " + i.getItem()[1]);
            System.out.println(i.getFacility());
        }

    }

    // to be used for facility datasets i.e. those with a trailing number indicating FacilityType
    public static Facility[] importFacilityData(String filename) {
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

        return facilities.toArray(new Facility[0]);
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
