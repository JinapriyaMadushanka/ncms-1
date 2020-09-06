package lk.spark.sample.service;


import lk.spark.sample.dao.Hospital;
import lk.spark.sample.repository.HospitalRepo;

public class HospitalServiceImpl implements HospitalService{
    @Override
    public String registerHospital(Hospital hospital) {
        HospitalRepo hospitalRepo = new HospitalRepo();
        String result = hospitalRepo.registerHospital(hospital);
        return result;
    }
}
