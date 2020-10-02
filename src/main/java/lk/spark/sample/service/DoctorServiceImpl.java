package lk.spark.sample.service;

import lk.spark.sample.dao.Doctor;
import lk.spark.sample.repository.DoctorRepo;

import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    @Override
    public String registerDoctor(Doctor doctor) {
        DoctorRepo doctorRepo = new DoctorRepo();
        String result = doctorRepo.doctorRegister(doctor);
        return result;
    }

    @Override
    public List notAdmitPatients() {
        DoctorRepo doctorRepo = new DoctorRepo();
        List<String> patientList = doctorRepo.getNewRegisteredPatients();
        return patientList;
    }

    @Override
    public String admitPatient(String doctorId, String patientId, String severityLevel) {
        DoctorRepo doctorRepo = new DoctorRepo();
        String result = doctorRepo.admitPatients(doctorId, patientId, severityLevel);
        return result;
    }

    @Override
    public String dischargePatient(String doctorId, String patientId) {
        DoctorRepo doctorRepo = new DoctorRepo();
        String result = doctorRepo.dischargePatients(doctorId, patientId);
        return result;
    }
    
    @Override
    public String changeDoctorRole(String doctoeId){
        DoctorRepo doctorRepo = new DoctorRepo();
        String result = doctorRepo.changeRole(doctorId);
        return result;
    }
}
