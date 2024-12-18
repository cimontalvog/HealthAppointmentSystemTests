/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package testcases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testcases.CustomerSupport;
import testcases.Doctor;
import java.time.*;
import java.util.*;
import org.junit.Assert;

/**
 *
 * @author cimontalvogalan
 */
public class TestCasesTestMain {
    
    private Doctor doctor;
    private Patient patient1;
    private Patient patient2;
    private Appointment appointment;
    private TimeSlot timeSlot1;
    private TimeSlot timeSlot2;
    private TimeSlot timeSlot3;
    private LocalDateTime dateTime1;
    private LocalDateTime dateTime2;
    private LocalDateTime dateTime3;
    private LocalDateTime dateTime4;
    private LocalDateTime dateTime5;
        
    public TestCasesTestMain() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        doctor = new Doctor(0, "Alan Harper", "Traumatologist");
        dateTime1 = LocalDateTime.parse("2024-12-20T10:00:00");
        dateTime2 = LocalDateTime.parse("2024-12-20T11:00:00");
        dateTime3 = LocalDateTime.parse("2024-12-20T12:00:00");
        dateTime4 = LocalDateTime.parse("2024-12-20T14:00:00");
        dateTime5 = LocalDateTime.parse("2024-12-20T16:00:00");
        patient1 = new Patient(0, "Daniel Allen", "3 Tomb Street, Dublin, Ireland");
        patient2 = new Patient(1, "Tom Ryan", "45 Dame Street, Cork, Ireland");
        appointment = new Appointment(doctor, patient1, 0, 1, dateTime1);
        doctor.appointments = new ArrayList();
        patient1.appointments = new ArrayList();
        patient2.appointments = new ArrayList();
        doctor.timeSlots = new ArrayList();
        doctor.appointments.add(appointment);
        patient1.appointments.add(appointment);
        timeSlot1 = new TimeSlot(0, doctor, dateTime1, dateTime2, true);
        timeSlot2 = new TimeSlot(1, doctor, dateTime2, dateTime3, false);
        timeSlot3 = new TimeSlot(2, doctor, dateTime4, dateTime5, false);
        doctor.timeSlots.add(timeSlot1);
        doctor.timeSlots.add(timeSlot2);
        doctor.timeSlots.add(timeSlot3);
    }
    
    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testRequestAppointment() {
        // We are asserting a time slot is not booked, then
        // we request an appointment and assert the patient has the appointment
        // apart from checking that time slot is booked.
        // Also, we check an Exception is thrown if that same time slot is
        // tried to be reserved.
        Assert.assertFalse(timeSlot2.getBooked());
        int appointmentId = patient1.requestAppointment(dateTime2, 1, doctor);
        Assert.assertTrue(patient1.appointments.stream().anyMatch(auxAppointment -> auxAppointment.getId() == appointmentId));
        Assert.assertTrue(timeSlot2.getBooked());
        Exception exception = Assert.assertThrows(UnsupportedOperationException.class, () -> {
            patient2.requestAppointment(dateTime2, 1, doctor);
        });
        Assert.assertEquals("Time slots not found", exception.getMessage());
    }
    
    @Test
    public void testCancelAppointment() {
        // We cancel the appointment and check it's not existing anymore
        // for both the patient and the doctor.
        doctor.cancelAppointment(patient1, appointment.getId());
        Assert.assertFalse(doctor.appointments.stream().anyMatch(auxAppointment -> auxAppointment.getId() == appointment.getId()));
        Assert.assertFalse(patient1.appointments.stream().anyMatch(auxAppointment -> auxAppointment.getId() == appointment.getId()));
    }
    
    @Test
    public void testGetAvailableTimeSlots() {
        // Considering we just have time slots in december,
        // we check it also for november
        Assert.assertFalse(!doctor.getAvailableTimeSlots(11).isEmpty());
        Assert.assertTrue(!doctor.getAvailableTimeSlots(12).isEmpty());
    }
    
    @Test
    public void testUpdateAppointmentStatus() {
        // We update the status of the appointment and then we
        // check the value is updated
        String newStatus = "DONE";
        appointment.updateStatus(newStatus);
        Assert.assertEquals(appointment.getStatus(), newStatus);
    }
    
    @Test
    public void testRescheduleAppointment() {
        // We reschedule an appointment and we can see the new time slot is booked
        assertFalse(timeSlot3.getBooked());
        patient1.rescheduleAppointment(appointment.getId(), dateTime4, 2, doctor);
        assertTrue(timeSlot3.getBooked());
    }
    
}
