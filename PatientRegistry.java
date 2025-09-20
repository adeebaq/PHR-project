package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PatientRegistry {
    private List<Patient> patients = new ArrayList<>();
    private static final String FILE_PATH = "patients.json";

    public PatientRegistry() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient findById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public Patient findByName(String fName, String lName) {
        for (Patient patient : patients) {
            if (patient.getFirstName().equalsIgnoreCase(fName) && patient.getLastName().equalsIgnoreCase(lName)) {
                return patient;
            }
        }
        return null;
    }

    public void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Patient> loadFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                CollectionType listType = mapper.getTypeFactory()
                        .constructCollectionType(ArrayList.class, Patient.class);
                return mapper.readValue(file, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(); // Return empty list if error or file doesn't exist
    }
}
