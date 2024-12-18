package testcases;

import java.time.*;

public class Appointment {

    Doctor doctor;
    Patient patient;
    private int id;
    private LocalDateTime datetime;
    private int duration;
    private String status;

    public Appointment(Doctor doctor, Patient patient, int id, int duration, LocalDateTime dateTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.id = id;
        this.duration = duration; // Duration is 1, 2 or 3. The hours the appointment is going to last
        this.datetime = dateTime;
        this.status = "IN_PROGRESS";
    }
    
    /**
     * 
     * @param status
     */
    public void updateStatus(String status) {
        System.out.println("Updating appointment status...");
        setStatus(status);
    }

    public String getAppointmentDetails() {
        System.out.println("Getting appointment details...");
        String details = "Doctor : " + doctor + "\n"
                + "Patient : " + patient + "\n"
                + "Id : " + id + "\n"
                + "Datetime : " + datetime.toString() + "\n"
                + "Duration : " + duration + "\n";
        return details;        
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
}