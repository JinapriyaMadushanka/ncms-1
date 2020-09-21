package lk.spark.sample.utill;

import java.util.UUID;

public final class Constants {
    //patient
    public static final String INSERT_PATIENT = "INSERT INTO patient (id, first_name, last_name, district, location_x, location_y, gender, contact, email, age ) VALUES (?,?,?,?,?,?,?,?,?,?)";

    //doctor
    public static final String INSERT_DOCTOR = "INSERT INTO doctor (id, name, hospital_id, is_director) VALUES (?,?,?,?)";
    public static final String ADMIT_PATIENT = "UPDATE patient SET (admit_date = ?, admitted_by = ?, severity_level=?) WHERE patient.id = ?";
    public static final String NEW_REGISTER_PATIENTS = "SELECT id FROM patient WHERE patient.admit_date IS NULL";
    public static final String DISCHARGE_PATIENT = "UPDATE patients SET (discharge_date = ?, discharged_by = ?) WHERE patient.id = ?";

    //hospital
    public static final String REGISTER_HOSPITAL = "INSERT INTO hospital (id, name, district, location_x, location_y, build_date) VALUES (?,?,?,?,?,?)";
    public static String createBedsForHospital(String uId){
        final String createbeds = "INSERT INTO hospital_bed (hospital_id) VALUES ('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "')," +
                "('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "')";
        return createbeds;
    }
    public static final String SELECT_HOSPITALS_WITH_BEDS = "SELECT DISTINCT hospital.id, hospital.location_x, hospital.location_y FROM hospital INNER JOIN hospital_bed ON hospital.id=hospital_bed.hospital_id AND hospital_bed.patient_id IS NULL";
    public static final String ASSIGN_PATIENT_TO_BED = "UPDATE hospital_bed SET hospital_bed.patient_id = ? WHERE hospital_bed.hospital_id = ? AND hospital_bed.patient_id IS NULL LIMIT 1";

    //queue
    public static final String INSERT_PATIENT_TO_QUEUE = "INSERT INTO patient_queue patient_id VALUES ?";

    //statistic
    public static final String GENARAL_STATISTICS = "SELECT COUNT(patient.id) As patientCount FROM patient";
    public static final String HOSPITAL_STATISTICS = "SELECT COUNT(hospital_bed.id) As bedCount FROM hospital_bed WHERE hospital_bed.patient_id IS NULL";

    //user
    public static final String REGISTER_USER = "INSERT INTO user (username, password, name, moh, hospital) VALUES (?, ?, ?, ?, ?)";
    public static final String LOGIN_USER = "SELECT name FROM user WHERE user.username = ? AND user.password = ?";
}
