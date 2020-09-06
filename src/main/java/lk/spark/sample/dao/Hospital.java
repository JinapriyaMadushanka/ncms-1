package lk.spark.sample.dao;

public class Hospital {
    private String name;
    private String district;
    private int locationX;
    private int locationY;

    public Hospital(String name, String district, int locationX, int locationY) {
        this.name = name;
        this.district = district;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
}
