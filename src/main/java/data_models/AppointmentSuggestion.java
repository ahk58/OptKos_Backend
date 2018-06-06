package data_models;

import java.time.LocalDateTime;

public class AppointmentSuggestion {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Service service;
    private Employee employee;

    public AppointmentSuggestion(LocalDateTime startTime, LocalDateTime endTime, Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        // service will remain null
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Service getService() {
        return service;
    }

    public Employee getEmployee() {
        return employee;
    }
}
