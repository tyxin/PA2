public class Facility {
    //this is our custom object to feed into node
    private double x_coord;
    private double y_coord;
    private FacilityType facilityType;
    private int rank;
    private int facilityQuality;

    public Facility(double x_coord, double y_coord, String facilityType, int rank, int facilityQuality){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.facilityType = FacilityType.valueOf(facilityType);
        this.rank = rank;
        this.facilityQuality = facilityQuality;
    }

    public double getDistanceTo(double x_coord_2, double y_coord_2){
        return Math.sqrt(Math.pow(Math.abs(x_coord_2-x_coord),2)+Math.pow(Math.abs(y_coord_2-y_coord),2));
    }

    public double getX_coord() { return x_coord; }

    public double getY_coord() { return y_coord; }

    public FacilityType getFacilityType() { return facilityType; }

    public int getRank() { return rank; }

    public int getFacilityQuality() { return facilityQuality; }

}
