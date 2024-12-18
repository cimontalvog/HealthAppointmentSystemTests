package testcases;

import java.time.*;

public class TimeSlot {

    private int id;
    Doctor doctor;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean booked;
    
    public TimeSlot(int id, Doctor doctor, LocalDateTime startTime, LocalDateTime endTime, boolean booked) {
        this.id = id;
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.booked = booked;
    }

    public boolean isAvailable() {
        return !getBooked();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean getBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
        
        
}