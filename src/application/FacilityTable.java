package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FacilityTable {

    private final SimpleStringProperty facilityTypeString = new SimpleStringProperty("");
    private final SimpleStringProperty facilityPathString = new SimpleStringProperty("");
    private final SimpleStringProperty facilityRankString = new SimpleStringProperty("");

    public FacilityTable(String facilityTypeString, String facilityPathString,
                         String facilityRankString){
        setFacilityTypeString(facilityTypeString);
        setFacilityPathString(facilityPathString);
        setFacilityRankString(facilityRankString);
    }

    public String getFacilityTypeString() {
        return facilityTypeString.get();
    }

    public void setFacilityTypeString(String facilityTypeString) {
        this.facilityTypeString.set(facilityTypeString);
    }

    public String getFacilityPathString() {
        return facilityPathString.get();
    }


    public void setFacilityPathString(String facilityPathString) {
        this.facilityPathString.set(facilityPathString);
    }

    public String getFacilityRankString() {
        return facilityRankString.get();
    }

    public void setFacilityRankString(String facilityRankString) {
        this.facilityRankString.set(facilityRankString);
    }


}
