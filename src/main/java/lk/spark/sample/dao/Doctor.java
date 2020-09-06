package lk.spark.sample.dao;

public class Doctor {
    private String name;
    private String hospitalId;
    private int isDirector;

    public Doctor(String name, String hospitalId, int isDirector) {
        this.name = name;
        this.hospitalId = hospitalId;
        this.isDirector = isDirector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(int isDirector) {
        this.isDirector = isDirector;
    }
}
