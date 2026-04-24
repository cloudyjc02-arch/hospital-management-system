package project;

import javafx.beans.property.*;  // Importing the necessary property classes from JavaFX

public class Employee {

    private final IntegerProperty employeeId;  // Integer property for employee ID
    private final StringProperty fullName;  // String property for employee full name
    private final IntegerProperty wage; // Integer property for employee wage
    private final IntegerProperty workHours;  // Integer property for employee work hours
    private final StringProperty position;  // String property for employee position

    // Constructor to initialize all fields
    public Employee(int employeeId, String fullName, int wage, int workHours, String position) {
        this.employeeId = new SimpleIntegerProperty(employeeId);  // Initialize employee ID
        this.fullName = new SimpleStringProperty(fullName);  // Initialize full name
        this.wage = new SimpleIntegerProperty(wage);  // Initialize wage
        this.workHours = new SimpleIntegerProperty(workHours);  // Initialize work hours
        this.position = new SimpleStringProperty(position);  // Initialize position
    }

    // Getters and Property Methods
    public int getEmployeeId() {
        return employeeId.get();  // Return employee ID
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);  // Set employee ID
    }

    public IntegerProperty employeeIdProperty() {
        return employeeId;  // Return employee ID property
    }

    public String getFullName() {
        return fullName.get();  // Return full name
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);  // Set full name
    }

    public StringProperty fullNameProperty() {
        return fullName;  // Return full name property
    }

    public int getWage() {
        return wage.get();  // Return wage
    }

    public void setWage(int wage) {
        this.wage.set(wage);  // Set wage
    }

    public IntegerProperty wageProperty() {
        return wage;  // Return wage property
    }

    public int getWorkHours() {
        return workHours.get();  // Return work hours
    }

    public void setWorkHours(int workHours) {
        this.workHours.set(workHours);  // Set work hours
    }

    public IntegerProperty workHoursProperty() {
        return workHours;  // Return work hours property
    }

    public String getPosition() {
        return position.get();  // Return position
    }

    public void setPosition(String position) {
        this.position.set(position);  // Set position
    }

    public StringProperty positionProperty() {
        return position;  // Return position property
    }
}