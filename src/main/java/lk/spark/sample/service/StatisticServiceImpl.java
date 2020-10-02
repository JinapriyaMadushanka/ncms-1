package lk.spark.sample.service;

import lk.spark.sample.repository.StatisticRepo;

import java.util.List;

public class StatisticServiceImpl implements StatisticService{
    @Override
    public int getStatisticForGeneral() {
        StatisticRepo statisticRepo = new StatisticRepo();
        int result = statisticRepo.StatisticForGeneral();
        return result;
    }

    @Override
    public List getStatisticForHospital() {
        StatisticRepo statisticRepo = new StatisticRepo();
        return statisticRepo.statisticForHospital();
    }
    
    @Override
    public List getStatisticForMoh() {
        StatisticRepo statisticRepo = new StatisticRepo();
        return statisticRepo.statisticForMoh();
    }
}
