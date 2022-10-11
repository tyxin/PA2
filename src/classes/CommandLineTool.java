package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommandLineTool {
    public static void main(String[] args) {

        String[] facilityTypes = {"Healthcare", "Primary Schools", "MRT", "Bus Exchange", "Secondary Schools"};
        ArrayList<String> facilityTypesList = new ArrayList<>(Arrays.asList(facilityTypes));

        ArrayList<Integer> rankingsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please key in numbers 1 to 5 depending on how " +
                "much you wish to prioritise each of the following facility types.");
        System.out.println("For example, 1 2 3 4 5");
        facilityTypesList.forEach(System.out::println);

        boolean success = false;
        while (!success) {
            String[] tokens = scanner.nextLine().split("[ ]+");
            try {
                Arrays.asList(tokens).forEach(s -> rankingsList.add(Integer.parseInt(s)));
            } catch (NumberFormatException e) {
                System.out.println("Number format exception!");
                rankingsList.clear();
            }

            if (rankChecker(rankingsList)) {
                success = true;
            } else {
                System.out.println("Please enter numbers 1 to 5 as indicated above!");
                rankingsList.clear();
            }
        }
        scanner.close();


        String[] filePrefixes = {"healthcare", "prisch", "mrt", "busexchange", "secsch"};

//        String estatepath = "src/resources/estate_locations.csv";
//        String ithpath = "src/resources/ith_locations.csv";

        String estatepath = System.getProperty("user.dir")+"/src/resources/estate_locations.csv";
        String ithpath = System.getProperty("user.dir")+"/src/resources/ith_locations.csv";

        ArrayList<String> facilitiesPaths = new ArrayList<>();
        for (String p: filePrefixes) {
            File f = new File(System.getProperty("user.dir")+"/src/resources/updated/"+p+"_locations.csv");
//            File f = new File("src/resources/updated/"+p+"_locations.csv");
            facilitiesPaths.add(f.getPath());
        }

        // import ITH locations data
        try {
            ConnectivityFinder connectivityFinder =
                    new ConnectivityFinder(facilityTypesList, rankingsList, facilitiesPaths, estatepath);
            connectivityFinder.assignConnectivity();
            ArrayList<HubLocation> sortedHubs = connectivityFinder.sortHubLocations(ithpath);

            connectivityFinder.printEstates();
            System.out.println();

            sortedHubs.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static boolean rankChecker(ArrayList<Integer> rankList){

        for (int i = 1; i <= rankList.size(); i++) {
            if (!rankList.contains(i)) { return false; }
        }

        return true;
    }
}
