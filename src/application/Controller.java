package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView facilityTableView;

    @FXML
    private TextField ITHTextField;

    @FXML
    private TextField facilityTypeLbl;

    @FXML
    private TextField facilityDirectoryLbl;

    @FXML
    private TextField rankFacilityLbl;

    @FXML
    private TextField estateTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private TableView resultTableView;

    private Model model;

    public void chooseFileITH(ActionEvent actionEvent) {
    }

    public void chooseFileFacility(ActionEvent actionEvent) {
        String filePath = model.RetrieveFacilityResouceFile();
        facilityDirectoryLbl.setText(filePath);
    }

    public void chooseEstateLocations(ActionEvent actionEvent) {
    }

    public void findOptimalITHButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
    }

    public void addFacility(ActionEvent actionEvent) {
        //depends on if table selected
        String facilityType = facilityTypeLbl.getText();
        String facilityFilePath = facilityDirectoryLbl.getText();
        String facilityRank = rankFacilityLbl.getText();
        //to insert in between
        facilityTableView.getSelectionModel().clearSelection();

    }

    public void editFacility(ActionEvent actionEvent) {
        String facilityType = facilityTypeLbl.getText();
        String facilityFilePath = facilityDirectoryLbl.getText();
        String facilityRank = rankFacilityLbl.getText();
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
                alert.setHeaderText("Fields are empty!");
                alert.setContentText("Please fill in required fields for successful edit");
                alert.showAndWait();
            }else{
//                data.remove(editIndex);
//                data.add(editIndex,new Person(firstNameTF.getText(),lastNameTF.getText(),emailTF.getText()));
//                firstNameTF.clear();
//                lastNameTF.clear();
//                tableView.setItems(data);
            }
        }
        facilityTableView.getSelectionModel().clearSelection();
    }

    public void deleteFacility(ActionEvent actionEvent) {
    }
}
