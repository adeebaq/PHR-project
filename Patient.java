package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private static int id = 0;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private Address address;
    private List<Double> heightList = new ArrayList();
    private List<Double> weightList = new ArrayList();
    private List<Double> heartRateList = new ArrayList();
    private List<String> notesList = new ArrayList();


    public Patient(String name, String surname, int age, String gender, String DOB, String phoneNumber, String email, Address address) {
        id++;
        this.firstName = name;
        this.lastName = surname;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = LocalDate.parse(DOB);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public Address getAddress() {
        return address;
    }
    public List<Double> getHeightList() {
        return heightList;
    }
    public List<Double> getWeightList() {
        return weightList;
    }
    public List<Double> getHeartRateList() {
        return heartRateList;
    }
    public List<String> getNotesList() {
        return notesList;
    }

    public void addHeight(double height, PatientVisit visit) {
        visit.setHeight(height);
        this.heightList.add(height);
    }

    public void addWeight(double weight, PatientVisit visit) {
        visit.setWeight(weight);
        this.weightList.add(weight);
    }

    public void addHeartRate(double heartRate, PatientVisit visit) {
        visit.setHeartRate(heartRate);
        this.heartRateList.add(heartRate);
    }

    public void addNote(String note, PatientVisit visit) {
        visit.setNotes(note);
        this.notesList.add(note);
    }


}
