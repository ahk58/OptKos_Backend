package util;

import data_loader.data_access_object.AppointmentDao;
import data_models.Appointment;

import manager.AppointmentManager;

import java.time.LocalDateTime;
import java.util.List;

public class TestBench {
    public static void main(String[] args) {
/*        AppointmentManager crack = new AppointmentManager();
        crack.generateIntervals("2018-04-13");*/
        AppointmentDao.getAllAppointmentsFromDb();
    }
}
