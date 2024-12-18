package testcases;

public class CustomerSupport {

    private int id;
    private int name;
    private int contactInfo;

    /**
     * 
     * @param appointmentId
     */
    public void assistWithAppointment(int appointmentId) {
        System.out.println("Assisting with appointment " + appointmentId);
    }

    /**
     * 
     * @param appointmentId
     */
    public void resolveIssue(int appointmentId) {
        System.out.println("Issue associated with " + appointmentId + "has been solved");
    }

    /**
     * 
     * @param appointmentId
     */
    public void logIssue(int appointmentId) {
        System.out.println("It has been detected a new issue for " + appointmentId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }
        
        

}