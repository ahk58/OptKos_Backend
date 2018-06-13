package manager;

import data_loader.data_access_object.AppointmentDao;
import data_loader.data_access_object.ServiceCounterDao;
import data_loader.data_access_object.WorkingWeekDao;
import data_models.Appointment;
import data_models.ServiceCounter;
import data_models.WorkingDay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticManager {
private int countEmployees = 0;

    public ServiceCounter getAllServiceCounter(String serviceId, String startTime, String endTime) {
        return ServiceCounterDao.getAllServiceCounter(serviceId, LocalDateTime.parse(startTime),
                LocalDateTime.parse(endTime));
    }

    public boolean getWorktimeStatistics(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long[] workingDays = getAllWorkingTimeSumForEachDay();
        long[] appointments = getAverageActualWorkingTime(startDate, endDate);
        boolean b = true;

        return b;
    }

    public long[] getAllWorkingTimeSumForEachDay(){
        ArrayList<WorkingDay> workingDays;
        workingDays = (ArrayList<WorkingDay>) WorkingWeekDao.getAllWorkingDaysFromDb();

        /*Count employees by getting the count of Mondays(to avoid another db query)*/
        List<WorkingDay> wd = workingDays.stream().filter(w -> w.getDay().equals("Montag"))
                .collect(Collectors.toList());
        countEmployees = wd.size();

        /*Calculate average Workingtime*/
        long[] workingWeek = new long[7];
        for(int i = 0; i<workingWeek.length; i++){
            for (WorkingDay day :
                    workingDays) {
                if(WorkingWeekDao.getDayIndex(day.getDay())==i)
                    workingWeek[i] += day.getWorkingTimeInMinutes();
            }
            workingWeek[i] /= countEmployees;
        }
        return workingWeek;
    }

    public long[] getAverageActualWorkingTime(LocalDate start, LocalDate end){
        List<Appointment> appointments = AppointmentDao.getAllAppointmentsInTimespan(start, end);

        /*Creating a list for each weekday*/
        ArrayList<ArrayList<Appointment>>appointmentsInWeek = new ArrayList<>();
        for(int i = 0; i<7; i++){
            final int index = i+1;
            ArrayList<Appointment> app = ((ArrayList) appointments.stream()
                    .filter(a -> a.getStartTime().getDayOfWeek().getValue() == index).collect(Collectors.toList()));

           ArrayList<Integer> sumOfWorkingtimeForEachDay = new ArrayList<>();



            appointmentsInWeek.add(app);
           appointments.removeAll(app);
        }

        /*Calculate average Appointment occupied time*/
        long[] actualWorkingTime = new long[7];
        int index = 0;
        for (ArrayList<Appointment> appointmentListDay :
                appointmentsInWeek) {
            for (Appointment appointment :
                    appointmentListDay) {
                actualWorkingTime[index] += appointment.getAppointmentDuration();
            }
            actualWorkingTime[index] /= 4;
            index++;
        }
        return actualWorkingTime;
    }
}
