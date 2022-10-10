package Model;

import java.util.ArrayList;

public class HubLocation {
    private double x_coord;
    private double y_coord;

    private String name;

    public HubLocation(String string) {
        this.name = string;
    }

    private double improvement;

    public double getDistanceTo(double x_coord_2, double y_coord_2){
        return Math.sqrt(Math.pow(Math.abs(x_coord_2-x_coord),2)+Math.pow(Math.abs(y_coord_2-y_coord),2));
    }

    public double getImprovement(){
        return improvement;
    }

    public void setImprovement(ArrayList<Location> list){

        double points = 0.0;

        for (int i=0;i<list.size();i++){
            Location location = list.get(i);
            points+=100/(location.getConnectivity()*getDistanceTo(location.getX_coord(), location.getY_coord()));
        }

        this.improvement = points;
    }

    public double getX_coord() { return x_coord; }

    public double getY_coord() { return y_coord; }

    public double[] getCoords() {
        return new double[]{x_coord, y_coord};
    }

    public String getName() { return name; }


}
