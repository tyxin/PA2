package application;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Model {

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

