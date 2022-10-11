package classes;

public class Facility {
    //this is our custom object to feed into node
    private double x_coord;
    private double y_coord;
    private String name;
    private String facilityType;
    private int rank;
    private int facilityQuality;


    public Facility(double x_coord, double y_coord, String facilityType, int rank, int facilityQuality){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.facilityType = facilityType;
        this.rank = rank;
        this.facilityQuality = facilityQuality;
    }

    public Facility(String data, String type, int rank) {
        String[] tokens = data.split(",");
        if (tokens.length > 4) {
            this.name = data.split("\"")[1];
            tokens = data.split("\"")[2].split(",");
            this.y_coord = Double.parseDouble(tokens[1]);
            this.x_coord = Double.parseDouble(tokens[2]);
            this.facilityQuality = Integer.parseInt(tokens[3]);
        } else {
            this.name = tokens[0];
            this.y_coord = Double.parseDouble(tokens[1]);
            this.x_coord = Double.parseDouble(tokens[2]);
            this.facilityQuality = Integer.parseInt(tokens[3]);
        }

        this.facilityType = type;
        this.rank = rank;
    }

    public double getDistanceTo(double x_coord_2, double y_coord_2){
        return Math.sqrt(Math.pow(Math.abs(x_coord_2-x_coord),2)+Math.pow(Math.abs(y_coord_2-y_coord),2));
    }

    public double getX_coord() { return x_coord; }

    public double getY_coord() { return y_coord; }

    public String getFacilityType() { return facilityType; }

    public int getRank() { return rank; }

    public int getFacilityQuality() { return facilityQuality; }

    @Override
    public String toString() {
        return "Model.Facility{" +
                "x_coord=" + x_coord +
                ", y_coord=" + y_coord +
                ", name='" + name + '\'' +
                ", facilityType=" + facilityType +
                ", rank=" + rank +
                ", facilityQuality=" + facilityQuality +
                '}';
    }

    public double[] getCoords() {
        return new double[]{x_coord, y_coord};
    }
}
