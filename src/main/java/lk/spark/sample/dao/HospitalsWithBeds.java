package lk.spark.sample.dao;

public class HospitalsWithBeds {
    private String hospitalId;
    private int locationX;
    private int locationY;
    private int bedId;

    public HospitalsWithBeds(String hospitalId, int locationX, int locationY) {
        this.hospitalId = hospitalId;
        this.locationX = locationX;
        this.locationY = locationY;
        //this.bedId = bedId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

//    public int getBedId() {
//        return bedId;
//    }
//
//    public void setBedId(int bedId) {
//        this.bedId = bedId;
//    }
}
