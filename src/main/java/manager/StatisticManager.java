package manager;

import data_loader.data_access_object.ServiceCounterDao;
import data_models.ServiceCounter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StatisticManager {

    public ServiceCounter getAllServiceCounter(String serviceId, String startTime, String endTime) {
        return ServiceCounterDao.getAllServiceCounter(serviceId, LocalDateTime.parse(startTime),
                LocalDateTime.parse(endTime));
    }

    public void getWorktimeStatistics(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
    }
}
