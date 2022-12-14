package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import classes.HubLocation;

public class Controller implements Initializable {

    @FXML
    private TableColumn rankITHCol;

    @FXML
    private TableColumn nameITHCol;

    @FXML
    private TableColumn latitudeITHCol;

    @FXML
    private TableColumn longitudeITHCol;

    @FXML
    private TableColumn facilityTypeCol;

    @FXML
    private TableColumn facilityDirectoryCol;

    @FXML
    private TableColumn facilityRankCol;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView facilityTableView;

    @FXML
    private TextField ITHTextField;

    @FXML
    private TextField facilityTypeTextField;

    @FXML
    private TextField facilityDirectoryTextField;

    @FXML
    private TextField rankFacilityTextField;

    @FXML
    private TextField estateTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private TableView resultTableView;

    private Model model;

    final ObservableList<FacilityTable> dataFacility = FXCollections.observableArrayList();
    final ObservableList<ITHTable> dataITH = FXCollections.observableArrayList();

    public void chooseFileITH(ActionEvent actionEvent) {
        String filePath = model.RetrieveFacilityResouceFile();
        ITHTextField.setText(filePath);
    }

    public void chooseFileFacility(ActionEvent actionEvent) {
        String filePath = model.RetrieveFacilityResouceFile();
        facilityDirectoryTextField.setText(filePath);
    }

    public void chooseEstateLocations(ActionEvent actionEvent) {
        String filePath = model.RetrieveFacilityResouceFile();
        estateTextField.setText(filePath);
    }

    public void findOptimalITHButton(ActionEvent actionEvent) {

        LocalDateTime start = LocalDateTime.now();

        try{
            model.initConnectivityFinder(estateTextField.getText(), new ArrayList<>(dataFacility));
            model.findBestITHLocations(ITHTextField.getText());

            ArrayList<Integer> rankArrayList = new ArrayList<>();

            for(int i = 0;i<dataFacility.size();i++){
                int tempRank = Integer.parseInt(dataFacility.get(i).getFacilityRankString());
                rankArrayList.add(tempRank);
            }

            if (!rankChecker(rankArrayList)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setResizable(true);
                alert.setTitle("Invalid Rank");
                alert.setHeaderText("Invalid Rank!");
                alert.setContentText("Please input continuous rank from 1 to n, where n is the number of facilities");
                alert.showAndWait();
            }else{
                dataITH.clear();

                ArrayList<HubLocation> sortedHubLocations = model.getSortedHubLocations();
                for (int i = 0;i<sortedHubLocations.size();i++){
                    HubLocation tempHubLocation = sortedHubLocations.get(i);
                    dataITH.add(new ITHTable(i+1,tempHubLocation.getName(),
                            tempHubLocation.getY_coord(),tempHubLocation.getX_coord()));
                }

                resultTableView.setItems(dataITH);
            }


        }catch (FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("File Not Found");
            alert.setHeaderText("Invalid file directory!");
            alert.setContentText("Please input all files required correctly in format required.");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setResizable(true);
            alert.setTitle("Unexpected Error Occured");
            alert.setHeaderText("Please select files with correct formatting");
            alert.setContentText("For facilities, the files must be formatted as:\n" +
                    "name,latitude,longitude,quality\n" +
                    "For integrated hubs and estate locations, the files must be formatted as:\n" +
                    "name,latitude,longitude\n" +
                    "Alternatively, please restart the application. Thank you!");
            alert.showAndWait();
        }

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Time Taken to Execute: "+ Duration.between(start,end).getSeconds()+" seconds");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        facilityTypeCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityTypeString"));
        facilityDirectoryCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityPathString"));
        facilityRankCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityRankString"));
        facilityTableView.setItems(dataFacility);

        rankITHCol.setCellValueFactory(new PropertyValueFactory<ITHTable, String>("ITHrank"));
        nameITHCol.setCellValueFactory(new PropertyValueFactory<ITHTable, String>("ITHname"));
        latitudeITHCol.setCellValueFactory(new PropertyValueFactory<ITHTable, String>("ITHlatitude"));
        longitudeITHCol.setCellValueFactory(new PropertyValueFactory<ITHTable,String>("ITHlongitude"));
        resultTableView.setItems(dataITH);

        facilityTableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
            if (newSelection!=null){
                int selectedIndex = facilityTableView.getSelectionModel().getSelectedIndex();
                facilityTypeTextField.setText(dataFacility.get(selectedIndex).getFacilityTypeString());
                facilityDirectoryTextField.setText(dataFacility.get(selectedIndex).getFacilityPathString());
                rankFacilityTextField.setText(dataFacility.get(selectedIndex).getFacilityRankString());

            }
        });
    }

    public void addFacility(ActionEvent actionEvent) {
        //depends on if table selected
        String facilityType = facilityTypeTextField.getText();
        String facilityFilePath = facilityDirectoryTextField.getText();
        String facilityRank = rankFacilityTextField.getText();
        //to insert in between
        if (!model.checkValidFacility(facilityType,facilityFilePath,facilityRank)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Fields are invalid!");
            alert.setContentText("Please fill in required fields with valid input for successful edit");
            alert.showAndWait();
        }else{
            dataFacility.add(new FacilityTable(facilityType,facilityFilePath,facilityRank));
            facilityTypeTextField.clear();
            facilityDirectoryTextField.clear();
            rankFacilityTextField.clear();
            facilityTableView.setItems(dataFacility);
            facilityTableView.getSelectionModel().clearSelection();
        }

    }

    public void editFacility(ActionEvent actionEvent) {
        String facilityType = facilityTypeTextField.getText();
        String facilityFilePath = facilityDirectoryTextField.getText();
        String facilityRank = rankFacilityTextField.getText();

        int editIndex = facilityTableView.getSelectionModel().getSelectedIndex();
        if (editIndex==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No row selected!");
            alert.setContentText("Please select a row before you attempt to edit");
            alert.showAndWait();
        }else{
            if (!model.checkValidFacility(facilityType,facilityFilePath,facilityRank)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setResizable(true);
                alert.setTitle("Warning");
                alert.setHeaderText("Fields are invalid!");
                alert.setContentText("Please fill in required fields with valid inputs for successful edit");
                alert.showAndWait();
            }else{
                dataFacility.remove(editIndex);
                dataFacility.add(editIndex,new FacilityTable(facilityType,facilityFilePath,facilityRank));
                facilityTypeTextField.clear();
                facilityDirectoryTextField.clear();
                rankFacilityTextField.clear();
                facilityTableView.setItems(dataFacility);
            }
        }
        facilityTableView.getSelectionModel().clearSelection();
    }

    public void deleteFacility(ActionEvent actionEvent) {
        int deleteIndex = facilityTableView.getSelectionModel().getSelectedIndex();
        if (deleteIndex==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No row selected!");
            alert.setContentText("Please select a row before you attempt to delete");
            alert.showAndWait();
        }else{
            dataFacility.remove(deleteIndex);
            facilityTableView.setItems(dataFacility);
        }
        facilityTableView.getSelectionModel().clearSelection();
    }

    private boolean rankChecker(ArrayList<Integer> rankList){

        for (int i = 1; i <= rankList.size(); i++) {
            if (!rankList.contains(i)) { return false; }
        }

        return true;
    }
}
