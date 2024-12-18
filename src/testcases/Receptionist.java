package testcases;

import java.time.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Receptionist {

    private int id;
    private String name;

    public Receptionist(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 
     * @param patient
     * @param doctor
     * @param dateTime
     * @param duration
     */
    public void scheduleAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime, int duration) {
        Integer maxIdForPatient = patient.appointments.stream().map(appointment -> appointment.getId()).max(Integer::compare).get();
        Integer maxIdForDoctor = doctor.appointments.stream().map(appointment -> appointment.getId()).max(Integer::compare).get();
        LocalDateTime endDateTime = dateTime.plusHours(duration);
                
        AtomicBoolean timeSlotFound = new AtomicBoolean(false);
        
        doctor.timeSlots.forEach((timeSlot) -> {
            if (
                    (timeSlot.getStartTime().isBefore(dateTime) || timeSlot.getStartTime().isEqual(dateTime)) &&
                    (timeSlot.getEndTime().isAfter(endDateTime) || timeSlot.getEndTime().isEqual(endDateTime)) &&
                    !timeSlot.getBooked()) {
                timeSlotFound.set(true);
            }
        });
        
        patient.appointments.forEach((appointment) -> {
            LocalDateTime appointmentEndDateTime = appointment.getDatetime().plusHours(appointment.getDuration());
            if (
                    (appointment.getDatetime().isBefore(dateTime) || appointment.getDatetime().isEqual(dateTime)) &&
                    (appointmentEndDateTime.isAfter(endDateTime) || appointmentEndDateTime.isEqual(endDateTime))){
                timeSlotFound.set(false);
            }
        });
        
        if (!timeSlotFound.get()) {
            throw new UnsupportedOperationException();
        }
        
        Appointment appointment = new Appointment(doctor, patient, Math.max(maxIdForPatient, maxIdForDoctor) + 1, duration, dateTime);
        patient.appointments.add(appointment);
        doctor.appointments.add(appointment);
    }

    /**
     * 
     * @param patient
     * @param doctor
     * @param appointmentId
     */
    public void cancelAppointment(Patient patient, Doctor doctor, int appointmentId) {
        patient.appointments.removeIf(appointment -> appointment.getId() == appointmentId);
        doctor.appointments.removeIf(appointment -> appointment.getId() == appointmentId);
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

}