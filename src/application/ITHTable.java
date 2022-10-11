package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ITHTable {

    private final SimpleStringProperty ITHrank = new SimpleStringProperty("");
    private final SimpleStringProperty ITHname = new SimpleStringProperty("");
    private final SimpleStringProperty ITHlatitude = new SimpleStringProperty("");
    private final SimpleStringProperty ITHlongitude = new SimpleStringProperty("");

    public ITHTable(int rank, String ithName, double ithLatitude,
                         double ithlongitude){
        setITHrank(String.valueOf(rank));
        setITHname(ithName);
        setITHlatitude(String.valueOf(ithLatitude));
        setITHlongitude(String.valueOf(ithlongitude));
    }

    public String getITHrank() { return ITHrank.get(); }

    public void setITHrank(String ITHrank) { this.ITHrank.set(ITHrank); }

    public String getITHname() { return ITHname.get(); }

    public void setITHname(String ITHname) { this.ITHname.set(ITHname); }

    public String getITHlatitude() { return ITHlatitude.get(); }

    public void setITHlatitude(String ITHlatitude) { this.ITHlatitude.set(ITHlatitude); }

    public String getITHlongitude() { return ITHlongitude.get(); }

    public void setITHlongitude(String ITHlongitude) { this.ITHlongitude.set(ITHlongitude); }

}
