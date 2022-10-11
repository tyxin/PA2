package application;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import classes.ConnectivityFinder;
import classes.HubLocation;

public class Model {
    private ConnectivityFinder connectivityFinder;
    private ArrayList<HubLocation> sortedHubLocations;

    public void initConnectivityFinder(String estateLocationsPath,
                                       ArrayList<FacilityTable> facilities) throws FileNotFoundException{

        ArrayList<String> facilityTypeList = new ArrayList<String>();
        ArrayList<String> filePathList = new ArrayList<String>();
        ArrayList<Integer> rankList = new ArrayList<Integer>();


        for (int i = 0;i<facilities.size();i++){
            rankList.add(Integer.parseInt(facilities.get(i).getFacilityRankString()));
            filePathList.add(facilities.get(i).getFacilityPathString());
            facilityTypeList.add(facilities.get(i).getFacilityTypeString());
        }

        connectivityFinder = new ConnectivityFinder(facilityTypeList, rankList, filePathList, estateLocationsPath);

        connectivityFinder.assignConnectivity();
    }

    public void findBestITHLocations(String ITHLocationsPath) throws FileNotFoundException{
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
        if (facilityType.trim().equals("")){
            return false;
        }
        boolean validType = facilityType.matches(regex);
        try{
            int rank = Integer.parseInt(facilityRank);
        }catch(NumberFormatException e){
            return false;
        }
        return validType;
    }

    public ArrayList<HubLocation> getSortedHubLocations() { return sortedHubLocations; }
}

