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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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

    final ObservableList<FacilityTable> data = FXCollections.observableArrayList();

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
        model.initConnectivityFinder(estateTextField.getText(), new ArrayList<>(data));
        model.findBestITHLocations(ITHTextField.getText());

        // TODO: update the ranked table list with the ranked list of ITH locations
        // that can be found in model.sortedHubLocations
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        facilityTypeCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityTypeString"));
        facilityDirectoryCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityPathString"));
        facilityRankCol.setCellValueFactory(new PropertyValueFactory<FacilityTable, String>("facilityRankString"));
        facilityTableView.setItems(data);

        facilityTableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
            if (newSelection!=null){
                int selectedIndex = facilityTableView.getSelectionModel().getSelectedIndex();
                facilityTypeTextField.setText(data.get(selectedIndex).getFacilityTypeString());
                facilityDirectoryTextField.setText(data.get(selectedIndex).getFacilityPathString());
                rankFacilityTextField.setText(data.get(selectedIndex).getFacilityRankString());

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
            data.add(new FacilityTable(facilityType,facilityFilePath,facilityRank));
            facilityTypeTextField.clear();
            facilityDirectoryTextField.clear();
            rankFacilityTextField.clear();
            facilityTableView.setItems(data);
            facilityTableView.getSelectionModel().clearSelection();
        }

    }

    public void editFacility(ActionEvent actionEvent) {
        String facilityType = facilityTypeTextField.getText();
        String facilityFilePath = facilityDirectoryTextField.getText();
        String facilityRank = rankFacilityTextField.getText();
        //TODO: refer to Y3 OOP II Lab 5.2

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
                alert.setTitle("Warning");
                alert.setHeaderText("Fields are invalid!");
                alert.setContentText("Please fill in required fields with valid inputs for successful edit");
                alert.showAndWait();
            }else{
                data.remove(editIndex);
                data.add(editIndex,new FacilityTable(facilityType,facilityFilePath,facilityRank));
                facilityTypeTextField.clear();
                facilityDirectoryTextField.clear();
                rankFacilityTextField.clear();
                facilityTableView.setItems(data);
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
            data.remove(deleteIndex);
            facilityTableView.setItems(data);
        }
        facilityTableView.getSelectionModel().clearSelection();
    }
}
