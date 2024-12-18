package testcases;

public class MedicalRecord {

    Patient patient;
    private int id;
    private String description;

    public String view() {
        System.out.println("Viewing description....");
        return getDescription();
    }

    /**
     * 
     * @param newRecord
     */
    public void update(String newRecord) {
        System.out.println("Updating description...");
        setDescription(newRecord);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    

}