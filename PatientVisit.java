package org.example;
import java.time.LocalDate;
import java.util.Scanner;

public class PatientVisit {
    Scanner input = new Scanner(System.in);
    LocalDate date;
    Patient patient;
    private String notes;
    private double height;
    private double weight;
    private double heartRate;

    public PatientVisit(Patient patient) {
        this.date = LocalDate.now();
        this.patient = patient;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }




}
