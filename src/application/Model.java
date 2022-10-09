package application;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Model.ConnectivityFinder;
import Model.HubLocation;

public class Model {
    ConnectivityFinder connectivityFinder;
    ArrayList<HubLocation> sortedHubLocations;

    public void initConnectivityFinder(String estateLocationsPath,
                                       ArrayList<FacilityTable> facilities) {
        // bruh idk whats going on here anymore
        // no clue how to use FacilityTable also
        // connectivityFinder = new ConnectivityFinder(??, estateLocationsPath)
        // first parameter should be a Map<String, Integer> of the different facilities

        connectivityFinder.assignConnectivity();
    }

    public void findBestITHLocations(String ITHLocationsPath) {
        this.sortedHubLocations = connectivityFinder.sortHubLocations(ITHLocationsPath);
    }


    public String RetrieveFacilityResouceFile(){
        FileChooser jfc = new FileChooser();
        jfc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = jfc.showOpenDialog(null);
        if (selectedFile==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No file selected!");
            alert.setContentText("Please select a file to add");
            alert.showAndWait();
            return null;
        }else{
            return selectedFile.getPath();
        }

    }

    public boolean checkValidFacility(String facilityType, String facilityFilePath, String facilityRank){
        //no commas allowed
        String regex = "^[^,]+$";
        boolean validType = facilityType.matches(regex);
        try{
            int rank = Integer.parseInt(facilityRank);
        }catch(NumberFormatException e){
            return false;
        }
        return validType;
    }
}

