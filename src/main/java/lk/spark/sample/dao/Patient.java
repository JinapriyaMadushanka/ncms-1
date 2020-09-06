package lk.spark.sample.dao;

public class Patient {
    private String firstName;
    private String lastName;
    private String district;
    private int locationX;
    private int locationY;
    private String gender;
    private String contact;
    private String email;
    private int age;

    public Patient(String firstName, String lastName, String district, int locationX, int locationY, String gender, String contact, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.district = district;
        this.locationX = locationX;
        this.locationY = locationY;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
