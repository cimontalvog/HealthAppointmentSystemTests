package testcases;

import java.util.*;
import java.time.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Patient {

    MedicalRecord medicalRecord;
    Collection<Appointment> appointments;
    private int id;
    private String name;
    private String contactInfo;
    
    public Patient(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    /**
     * 
     * @param dateTime
     * @param duration
     * @param doctor
     */
    public int requestAppointment(LocalDateTime dateTime, int duration, Doctor doctor) {
        Integer maxIdForPatient = appointments.stream().map(appointment -> appointment.getId()).max(Integer::compare).orElse(0);
        Integer maxIdForDoctor = doctor.appointments.stream().map(appointment -> appointment.getId()).max(Integer::compare).orElse(0);
        LocalDateTime endDateTime = dateTime.plusHours(duration);
                
        AtomicBoolean timeSlotFound = new AtomicBoolean(false);
        
        List<TimeSlot> timeSlots = doctor.timeSlots.stream().filter((timeSlot) -> {
            if (
                    (timeSlot.getStartTime().isBefore(dateTime) || timeSlot.getStartTime().isEqual(dateTime)) &&
                    (timeSlot.getEndTime().isAfter(endDateTime) || timeSlot.getEndTime().isEqual(endDateTime)) &&
                    !timeSlot.getBooked()) {
                timeSlotFound.set(true);
            }
            return true;
        }).toList();
        
        appointments.forEach((appointment) -> {
            LocalDateTime appointmentEndDateTime = appointment.getDatetime().plusHours(appointment.getDuration());
            if (
                    (appointment.getDatetime().isBefore(dateTime) || appointment.getDatetime().isEqual(dateTime)) &&
                    (appointmentEndDateTime.isAfter(endDateTime) || appointmentEndDateTime.isEqual(endDateTime))){
                timeSlotFound.set(false);
            }
        });
        
        if (!timeSlotFound.get()) {
            throw new UnsupportedOperationException("Time slots not found");
        }
        
        Appointment appointment = new Appointment(doctor, this, Math.max(maxIdForPatient, maxIdForDoctor) + 1, duration, dateTime);
        appointments.add(appointment);
        doctor.appointments.add(appointment);
        timeSlots.forEach(timeSlot -> timeSlot.setBooked(true));
        
        return appointment.getId();
    }

    /**
     * 
     * @param appointmentId
     * @param doctor
     */
    public void cancelAppointment(int appointmentId, Doctor doctor) {
        appointments.removeIf(appointment -> appointment.getId() == appointmentId);
        doctor.appointments.removeIf(appointment -> appointment.getId() == appointmentId);
    }

    /**
     * 
     * @param appointmentId
     * @param newDateTime
     * @param duration
     * @param doctor
     */
    public void rescheduleAppointment(int appointmentId, LocalDateTime newDateTime, int duration, Doctor doctor) {
        Appointment previousAppointment = doctor.appointments.stream().filter(auxAppointment -> auxAppointment.getId() == appointmentId).findFirst().get();
        LocalDateTime endExistingDateTime = previousAppointment.getDatetime().plusHours(previousAppointment.getDuration());
        doctor.timeSlots.stream().forEach((timeSlot) -> {
            if ((timeSlot.getStartTime().isAfter(previousAppointment.getDatetime()) || timeSlot.getStartTime().isEqual(previousAppointment.getDatetime())) &&
                    (timeSlot.getEndTime().isBefore(endExistingDateTime) || timeSlot.getEndTime().isEqual(endExistingDateTime))) {
                timeSlot.setBooked(false);
            }
        });
        
        LocalDateTime endNewDateTime = newDateTime.plusHours(duration);
                
        AtomicBoolean timeSlotFound = new AtomicBoolean(false);
        
        List<TimeSlot> timeSlots = doctor.timeSlots.stream().filter((timeSlot) -> {
            if (
                    (timeSlot.getStartTime().isBefore(newDateTime) || timeSlot.getStartTime().isEqual(newDateTime)) &&
                    (timeSlot.getEndTime().isAfter(endNewDateTime) || timeSlot.getEndTime().isEqual(endNewDateTime)) &&
                    !timeSlot.getBooked()) {
                timeSlotFound.set(true);
            }
            return true;
        }).toList();
        
        appointments.forEach((appointment) -> {
            LocalDateTime appointmentEndDateTime = appointment.getDatetime().plusHours(appointment.getDuration());
            if (
                    (appointment.getDatetime().isBefore(newDateTime) || appointment.getDatetime().isEqual(newDateTime)) &&
                    (appointmentEndDateTime.isAfter(endNewDateTime) || appointmentEndDateTime.isEqual(endNewDateTime))){
                timeSlotFound.set(false);
            }
        });
        
        if (!timeSlotFound.get()) {
            throw new UnsupportedOperationException();
        }
        
        timeSlots.forEach(timeSlot -> timeSlot.setBooked(true));
        previousAppointment.setDatetime(newDateTime);
        previousAppointment.setDuration(duration);
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    

}