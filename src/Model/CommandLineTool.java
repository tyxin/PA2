package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandLineTool {
    public static void main(String[] args) {

        String[] facilityTypes = {"Healthcare", "Primary Schools", "MRT", "Bus Exchange", "Secondary Schools"};
        ArrayList<String> facilityTypesList = new ArrayList<>(Arrays.asList(facilityTypes));
        Integer[] rankings = {1, 2, 3, 5, 4};
        ArrayList<Integer> rankingsList = new ArrayList<>(Arrays.asList(rankings));

        String[] filePrefixes = {"healthcare", "prisch", "mrt", "busexchange", "secsch"};

        String estatepath = "src/resources/estate_locations.csv";
        String ithpath = "src/resources/ith_locations.csv";

        ArrayList<String> facilitiesPaths = new ArrayList<>();
        for (String p: filePrefixes) {
            File f = new File("src/resources/updated/"+p+"_locations.csv");
            facilitiesPaths.add(f.getPath());
        }

        // import ITH locations data
        try {
            ConnectivityFinder connectivityFinder =
                    new ConnectivityFinder(facilityTypesList, rankingsList, facilitiesPaths, estatepath);
            connectivityFinder.assignConnectivity();
            ArrayList<HubLocation> sortedHubs = connectivityFinder.sortHubLocations(ithpath);

            sortedHubs.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
