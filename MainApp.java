package org.example;
import java.util.List;
 import javafx.application.Application;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.DatePicker;
 import javafx.scene.control.TextField;
 import javafx.scene.control.Label;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.VBox;
 import javafx.stage.Stage;

 public class MainApp extends Application {
  PatientRegistry patientRegistry = new PatientRegistry();
  @Override
  public void start(Stage primaryStage) {
    VBox root = new VBox(new Label("Hello JavaFX!"));
    Scene scene = new Scene(root, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Patient Health Record");
    primaryStage.show();


  // Create buttons
  Button returningButton = new Button("Returning Patient");
  Button newButton = new Button("New Patient");

  Button patientLog = new Button("Patient Log");
  patientLog.setOnAction(e -> {
   patientRegistry.loadFromFile();
  });

  // Button actions
  returningButton.setOnAction(e -> openReturningPatientScreenInSameWindow(primaryStage));
  newButton.setOnAction(e -> openNewPatientScreenInSameWindow(primaryStage));

  // Main layout
  VBox mainLayout = new VBox(15, returningButton, newButton, patientLog);
  mainLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");

  Scene mainScene = new Scene(mainLayout, 400, 400);
  primaryStage.setScene(mainScene);
  primaryStage.centerOnScreen();
  primaryStage.show();
  }

  private void openNewPatientScreenInSameWindow(Stage primaryStage) {
   TextField firstNameField = new TextField();
   TextField lastNameField = new TextField();
   TextField ageField = new TextField();
   TextField phoneField = new TextField();
   TextField genderField = new TextField();
   TextField emailField = new TextField();
   DatePicker birthDatePicker = new DatePicker();
   TextField streetField = new TextField();
   TextField cityField = new TextField();
   TextField stateField = new TextField();
   TextField zipField = new TextField();
   TextField countryField = new TextField();

   Button submitButton = new Button("Submit");
   submitButton.setOnAction(e -> {
    try {
     String fName = firstNameField.getText();
     String lName = lastNameField.getText();
     int age = Integer.parseInt(ageField.getText());
     String gender = genderField.getText();
     String phone = phoneField.getText();
     String email = emailField.getText();
     String birthDate = birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "";
     String streetAddress = streetField.getText();
     String city = cityField.getText();
     String state = stateField.getText();
     String zipCode = zipField.getText();
     String country = countryField.getText();

     Address address = new Address(streetAddress, city, state, zipCode, country);

     Patient patient = new Patient(fName, lName, age, gender, birthDate, phone, email, address);
     patientRegistry.addPatient(patient);
     patientRegistry.saveToFile();

     System.out.println("Added new patient: " + fName + " " + lName);

     // Go to new visit screen
     openPatientVisitScreen(primaryStage, patient);

    } catch (NumberFormatException ex) {
     System.out.println("Invalid age entered");
     // You can also show an alert dialog here
    }
   });

   Button backButton = new Button("← Back");
   backButton.setOnAction(e -> start(primaryStage));

   VBox layout = new VBox(10,
           new Label("New Patient Form"),
           new Label("First Name:"), firstNameField,
           new Label("Last Name:"), lastNameField,
           new Label("Age:"), ageField,
           new Label("Gender:"), genderField,
           new Label("Phone:"), phoneField,
           new Label("Email:"), emailField,
           new Label("Birth Date:"), birthDatePicker,
           new Label("Street Address:"), streetField,
           new Label("City:"), cityField,
           new Label("State:"), stateField,
           new Label("Zip Code:"), zipField,
           new Label("Country:"), countryField,
           submitButton,
           backButton
   );

   layout.setStyle("-fx-padding: 20; -fx-alignment: center-left;");
   primaryStage.setScene(new Scene(layout, 1200, 900));
   primaryStage.centerOnScreen();
  }



  private void openReturningPatientScreenInSameWindow(Stage primaryStage) {
   TextField firstNameField = new TextField();
   TextField lastNameField = new TextField();

   Button submitButton = new Button("Submit");
   submitButton.setOnAction(e -> {
            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            Patient current = patientRegistry.findByName(fName, lName);
            openPatientVisitScreen(primaryStage, current);
   });
   Button homeButton = new Button("← Back to Home");
   homeButton.setOnAction(e -> start(primaryStage));

   VBox layout = new VBox(10,
           new Label("Returning Patient Screen"),
           new Label("First Name:"), firstNameField,
           new Label("Last Name:"), lastNameField,
           submitButton, homeButton
   );
   layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
   Scene newScene = new Scene(layout, 1200, 900);
   primaryStage.setScene(newScene);
   primaryStage.centerOnScreen();
  }

  private void openPatientVisitScreen(Stage primaryStage, Patient patient) {
   VBox layout = new VBox(10);
   HBox hbox = new HBox(10);
   PatientVisit newVisit = new PatientVisit(patient);
   Label message = new Label("Patient Created Successfully!");
   Label name = new Label("Name: " + patient.getFirstName() + " " + patient.getLastName());
   Label age = new Label("Age: " + patient.getAge());
   Label phone = new Label("Phone: " + patient.getPhoneNumber());
   Label email = new Label("Email: " + patient.getEmail());

   TextField currentHeight = new TextField();
   TextField currentWeight = new TextField();
   TextField currentHR = new TextField();
   Button confirmButton = new Button("Confirm");

   confirmButton.setOnAction(e -> {
    try {
     double height = Double.parseDouble(currentHeight.getText());
     double weight = Double.parseDouble(currentWeight.getText());
     double heartRate = Double.parseDouble(currentHR.getText());

     patient.addHeight(height, newVisit);
     patient.addWeight(weight, newVisit);
     patient.addHeartRate(heartRate, newVisit);

     System.out.println("Vitals recorded!");
    } catch (NumberFormatException ex) {
     System.out.println("Invalid input. Please enter valid numbers.");
    }
   });



   Button homeButton = new Button("Back to Home");
   homeButton.setOnAction(e -> start(primaryStage));
   Button notesButton = new Button("Notes: ");
   notesButton.setOnAction(e -> {
    TextField textbox = new TextField();
    textbox.setPromptText("Enter your notes here:");

    Button saveNoteButton = new Button("Save Note");
    saveNoteButton.setOnAction(event -> {
     String note = textbox.getText();
     if (!note.trim().isEmpty()) {
      patient.addNote(note, newVisit);
      System.out.println("Note saved!");
     } else {
      System.out.println("Note is empty.");
     }
    });
    layout.getChildren().addAll( new Label("Note:"), textbox, saveNoteButton);
   });

   Button confirmEntries = new Button("Confirm Entries");
   confirmEntries.setOnAction(e -> {
    double height = Double.parseDouble(currentHeight.getText());
    double weight = Double.parseDouble(currentWeight.getText());
    double heartRate = Double.parseDouble(currentHR.getText());
    patient.addHeight(height, newVisit );
    patient.addWeight(weight, newVisit);
    patient.addHeartRate(heartRate, newVisit);
   });
   hbox.getChildren().addAll(message, name, age, phone, email);
   layout.getChildren().addAll(
           new Label("Weight: "), currentWeight,
           new Label("Height: "), currentHeight,
           new Label("Heart Rate: "), currentHR, confirmEntries, notesButton,
           homeButton);
   layout.setStyle("-fx-padding: 20; -fx-alignment: center;");



   Scene scene = new Scene(layout, 1200, 900);
   primaryStage.setScene(scene);
   primaryStage.centerOnScreen();
  }

  private void openPatientHistoryScreen(Stage primaryStage, Patient patient) {
   VBox layout = new VBox(10);
   HBox hbox = new HBox(10);
   Label message = new Label("Patient History");
   Label name = new Label("Name: " + patient.getFirstName() + " " + patient.getLastName());
   Label age = new Label("Age: " + patient.getAge());
   Label phone = new Label("Phone: " + patient.getPhoneNumber());
   Label email = new Label("Email: " + patient.getEmail());
   Label birthDate = new Label("Birth Date: " + patient.getDateOfBirth());
   Label address = new Label("Address: " + patient.getAddress());

   hbox.getChildren().addAll(message, name, age, phone, email, birthDate, address);

   Button homeButton = new Button("Home");
   Button notesButton = new Button("Notes History");
   notesButton.setOnAction(e -> {
    List<String> notes = patient.getNotesList();
    if (notes != null) {
     for (String note : notes) {
      System.out.println(note);
     }
    }


   });

   layout.getChildren().addAll(
           new Label("Height List: "), new Label("Weight List: "), new Label("Heart Rate List: "),
           hbox, notesButton, homeButton
   );

  }

  public static void main(String[] args) {
  launch(args);
  }
}
