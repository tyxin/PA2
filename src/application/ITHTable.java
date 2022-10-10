package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ITHTable {

    private final SimpleStringProperty ITHrank = new SimpleStringProperty("");
    private final SimpleStringProperty ITHname = new SimpleStringProperty("");
    private final SimpleStringProperty ITHlatitude = new SimpleStringProperty("");
    private final SimpleStringProperty ITHlongtitude = new SimpleStringProperty("");

    public ITHTable(int rank, String ithName, double ithLatitude,
                         double ithLongtitude){
        setITHrank(String.valueOf(rank));
        setITHname(ithName);
        setITHlatitude(String.valueOf(ithLatitude));
        setITHlongtitude(String.valueOf(ithLongtitude));
    }

    public String getITHrank() { return ITHrank.get(); }

    public void setITHrank(String ITHrank) { this.ITHrank.set(ITHrank); }

    public String getITHname() { return ITHname.get(); }

    public void setITHname(String ITHname) { this.ITHname.set(ITHname); }

    public String getITHlatitude() { return ITHlatitude.get(); }

    public void setITHlatitude(String ITHlatitude) { this.ITHlatitude.set(ITHlatitude); }

    public String getITHlongtitude() { return ITHlongtitude.get(); }

    public void setITHlongtitude(String ITHlongtitude) { this.ITHlongtitude.set(ITHlongtitude); }

}
