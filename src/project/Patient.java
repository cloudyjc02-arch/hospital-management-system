package project;

import javafx.beans.property.*;  // Import JavaFX property classes

public class Patient {

    private final IntegerProperty patientId;  // Property for patient's ID
    private final StringProperty name;  // Property for patient's first name
    private final IntegerProperty age;  // Property for patient's age
    private final StringProperty condition;  // Property for patient's medical condition
    private final StringProperty lastName;  // Property for patient's last name

    // Constructor
    public Patient(int patientId, String name, int age, String condition, String lastName) {
        this.patientId = new SimpleIntegerProperty(patientId);  // Initialize patientId property
        this.name = new SimpleStringProperty(name);  // Initialize name property
        this.age = new SimpleIntegerProperty(age);  // Initialize age property
        this.condition = new SimpleStringProperty(condition);  // Initialize condition property
        this.lastName = new SimpleStringProperty(lastName);  // Initialize lastName property
    }

    // Getter and Property Methods
    public int getPatientId() {
        return patientId.get();  // Return patient's ID
    }

    public IntegerProperty patientIdProperty() {
        return patientId;  // Return property for patientId
    }

    public String getName() {
        return name.get();  // Return patient's first name
    }

    public StringProperty nameProperty() {
        return name;  // Return property for name
    }

    public int getAge() {
        return age.get();  // Return patient's age
    }

    public IntegerProperty ageProperty() {
        return age;  // Return property for age
    }

    public String getCondition() {
        return condition.get();  // Return patient's medical condition
    }

    public StringProperty conditionProperty() {
        return condition;  // Return property for condition
    }

    public String getLastName() {
        return lastName.get();  // Return patient's last name
    }

    public StringProperty lastNameProperty() {
        return lastName;  // Return property for lastName
    }
}