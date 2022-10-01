public class Facility {
    //this is our custom object to feed into node
    private int x_coord;
    private int y_coord;
    private FacilityType facilityType;
    private int rank;
    private int facilityQuality;

    public Facility(int x_coord, int y_coord, String facilityType, int rank, int facilityQuality){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.facilityType = FacilityType.valueOf(facilityType);
        this.rank = rank;
        this.facilityQuality = facilityQuality;
    }
}
