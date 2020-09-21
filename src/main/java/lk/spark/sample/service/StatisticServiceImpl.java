package lk.spark.sample.service;

import lk.spark.sample.repository.StatisticRepo;

import java.util.List;

public class StatisticServiceImpl implements StatisticService{
    StatisticRepo statisticRepo;
    @Override
    public int getStatisticForGeneral() {
        statisticRepo = new StatisticRepo();
        int result = statisticRepo.StatisticForGeneral();
        return result;
    }

    @Override
    public List getStatisticForHospital() {
        statisticRepo = new StatisticRepo();
        return statisticRepo.statisticForHospital();
    }
}
