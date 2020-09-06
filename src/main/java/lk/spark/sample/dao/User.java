package lk.spark.sample.dao;

public class User {
    private String userName;
    private String password;
    private String name;
    private int moh;
    private int hospital;

    public User(String userName, String password, String name, int moh, int hospital) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.moh = moh;
        this.hospital = hospital;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoh() {
        return moh;
    }

    public void setMoh(int moh) {
        this.moh = moh;
    }

    public int getHospital() {
        return hospital;
    }

    public void setHospital(int hospital) {
        this.hospital = hospital;
    }
}
