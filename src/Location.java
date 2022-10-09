import java.util.*;
public class Location {
    //this is our custom object to feed into node
    private double x_coord;
    private double y_coord;
    private String name;

    private double connectivity;

    public Location(double x_coord, double y_coord, String name){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.name = name;
        this.connectivity = -1.0;
    }

    public Location(String data) {
        String[] tokens = data.split(",");
        this.name = tokens[0];
        this.y_coord = Double.parseDouble(tokens[1]);
        this.x_coord = Double.parseDouble(tokens[2]);
        this.connectivity = -1.0;
    }

    public double getDistanceTo(double x_coord_2, double y_coord_2){
        return Math.sqrt(Math.pow(Math.abs(x_coord_2-x_coord),2)+Math.pow(Math.abs(y_coord_2-y_coord),2));
    }


    public void setConnectivity(ArrayList<Facility> list){
        
        double points = 0.0;

        for (int i=0;i<list.size();i++){
            Facility facility = list.get(i);
            points+=facility.getFacilityQuality()/getDistanceTo(facility.getX_coord(), facility.getY_coord());
        }
        
        points/=(double)list.size();
        
        connectivity = points;

    }


    public double getImprovement(ArrayList<Location> list){

        double points = 0.0;

        for (int i=0;i<list.size();i++){
            Location location = list.get(i);
            points+=100/(location.getConnectivity()*getDistanceTo(location.getX_coord(), location.getY_coord()));
        }

        return points;
    }

    public double getX_coord() { return x_coord; }

    public double getY_coord() { return y_coord; }

    public double[] getCoords() {
        return new double[]{x_coord, y_coord};
    }

    @Override
    public String toString() {
        return "Location{" +
                "x_coord=" + x_coord +
                ", y_coord=" + y_coord +
                ", name='" + name + '\'' +
                '}';
    }


    public double getConnectivity() {
        return connectivity;
    }
}
