package application;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Model {

    public String RetrieveFacilityResouceFile(){
        FileChooser jfc = new FileChooser();
        jfc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = jfc.showOpenDialog(null);
        String filePath = selectedFile.getPath();
        return filePath;

    }

    public boolean checkValidFacility(String facilityType, String facilityFilePath, String facilityRank){
        return false;
    }
}

