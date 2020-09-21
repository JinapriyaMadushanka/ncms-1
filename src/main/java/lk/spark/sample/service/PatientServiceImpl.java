package lk.spark.sample.service;

import lk.spark.sample.dao.HospitalsWithBeds;
import lk.spark.sample.dao.Patient;
import lk.spark.sample.repository.HospitalRepo;
import lk.spark.sample.repository.PatientRepo;
import lk.spark.sample.repository.QueueRepo;

import java.util.ArrayList;

public class PatientServiceImpl implements PatientService {
    @Override
    public String registerPetient(Patient patient) {
        PatientRepo patientRepo = new PatientRepo();
        String result = patientRepo.addPatient(patient);
        if(result.equals("")){
            HospitalRepo hospitalRepo = new HospitalRepo();
            ArrayList<HospitalsWithBeds> hospitalsWithBedsArrayList = hospitalRepo.selectHospitalsWithBeds();
            if(hospitalsWithBedsArrayList.isEmpty()){
                QueueRepo queueRepo = new QueueRepo();
                return queueRepo.insertIntoQueue(result);
            }else{
                float distance = 0;
                String hospitalId = null;
                for (HospitalsWithBeds hospitalsWithBeds: hospitalsWithBedsArrayList) {
                    float sqDistanceX = (float) Math.pow((patient.getLocationX() - hospitalsWithBeds.getLocationX()),2);
                    float sqDistanceY = (float) Math.pow((patient.getLocationY() - hospitalsWithBeds.getLocationY()),2);
                    float newDistance = (float) Math.sqrt(sqDistanceX+sqDistanceY);
                    if(distance == 0 || distance > newDistance){
                        distance = newDistance;
                        hospitalId = hospitalsWithBeds.getHospitalId();
                    }
                }
                hospitalRepo.admitPatient(hospitalId, result);
            }
            return "Patient registration ";
        }else {
            return "Patient registration failed.";
        }
    }
}
