package lk.spark.sample.service;

import lk.spark.sample.dao.Doctor;
import lk.spark.sample.repository.DoctorRepo;

import java.util.List;


public class DoctorServiceImpl implements DoctorService{
    private DoctorRepo doctorRepo;

    @Override
    public String registerDoctor(Doctor doctor) {
        doctorRepo = new DoctorRepo();
        String result = doctorRepo.doctorRegister(doctor);
        return result;
    }

    @Override
    public List notAdmitPatients() {
        doctorRepo = new DoctorRepo();
        List<String> patientList = doctorRepo.getNewRegisteredPatients();
        return patientList;
    }

    @Override
    public String admitPatient(String doctorId, String patientId, String severityLevel) {
        doctorRepo = new DoctorRepo();
        String result = doctorRepo.admitPatients(doctorId, patientId, severityLevel);
        return result;
    }

    @Override
    public String dischargePatient(String doctorId, String patientId) {
        doctorRepo = new DoctorRepo();
        String result = doctorRepo.dischargePatients(doctorId, patientId);
        return result;
    }
}
