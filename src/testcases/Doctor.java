package testcases;

import java.util.*;

public class Doctor {

    Collection<TimeSlot> timeSlots;
    Collection<Appointment> appointments;
    private int id;
    private String name;
    private String specialty;
    
    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    /**
     * 
     * @param patient
     * @param appointmentId
     */
    public void cancelAppointment(Patient patient, int appointmentId) {
        patient.appointments.removeIf(appointment -> appointment.getId() == appointmentId);
        appointments.removeIf(appointment -> appointment.getId() == appointmentId);
    }

    /**
     * 
     * @param month
     */
    public List<TimeSlot> getAvailableTimeSlots(int month) {
        if (month < 1 || month > 12) {
            throw new UnsupportedOperationException();
        }
        return timeSlots.stream().filter(timeSlot -> timeSlot.getStartTime().getMonthValue() == month).toList();
    }

    public Collection<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Collection<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    

}