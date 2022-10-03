public class Location {
    //this is our custom object to feed into node
    private double x_coord;
    private double y_coord;
    private String name;

    public Location(double x_coord, double y_coord, String name){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.name = name;
    }

    public Location(String data) {
        String[] tokens = data.split(",");
        this.name = tokens[0];
        this.y_coord = Double.parseDouble(tokens[1]);
        this.x_coord = Double.parseDouble(tokens[2]);
    }

    public double getDistanceTo(double x_coord_2, double y_coord_2){
        return Math.sqrt(Math.pow(Math.abs(x_coord_2-x_coord),2)+Math.pow(Math.abs(y_coord_2-y_coord),2));
    }

    public double getX_coord() { return x_coord; }

    public double getY_coord() { return y_coord; }

    public Double[] getCoords() {
        return new Double[]{x_coord, y_coord};
    }

    @Override
    public String toString() {
        return "Location{" +
                "x_coord=" + x_coord +
                ", y_coord=" + y_coord +
                ", name='" + name + '\'' +
                '}';
    }
}
