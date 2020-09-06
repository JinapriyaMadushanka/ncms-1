package lk.spark.sample.service;

import lk.spark.sample.dao.Doctor;

import java.util.List;

public interface DoctorService {
    public String registerDoctor(Doctor doctor);

    public List notAdmitPatients();

    public String admitPatient(String doctorId, String patientId, String severityLevel);

    public String dischargePatient(String doctorId, String patientId);
}
