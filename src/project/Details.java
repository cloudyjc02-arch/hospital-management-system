package project;  // This is the package where the class belongs.

import javafx.beans.property.*;  // Importing the Property classes for data binding.

public class Details {  // This defines a class called Details.

    private final StringProperty item;  // A property for the "Item" column in the table.
    private final StringProperty details;  // A property for the "Details" column in the table.

    // Constructor to initialize both fields
    public Details(String item, String details) {  // Constructor to create a Details object with item and details values.
        this.item = new SimpleStringProperty(item);  // Initialize the "Item" property with the given value.
        this.details = new SimpleStringProperty(details);  // Initialize the "Details" property with the given value.
    }

    // Getter and Property Methods for "Item"
    public String getItem() {  // This method returns the value of the "Item" property.
        return item.get();  // Return the value of the "Item" property.
    }

    public void setItem(String item) {  // This method sets the value of the "Item" property.
        this.item.set(item);  // Set the value of the "Item" property.
    }

    public StringProperty itemProperty() {  // This method returns the property for "Item".
        return item;  // Return the property for "Item".
    }

    // Getter and Property Methods for "Details"
    public String getDetails() {  // This method returns the value of the "Details" property.
        return details.get();  // Return the value of the "Details" property.
    }

    public void setDetails(String details) {  // This method sets the value of the "Details" property.
        this.details.set(details);  // Set the value of the "Details" property.
    }

    public StringProperty detailsProperty() {  // This method returns the property for "Details".
        return details;  // Return the property for "Details".
    }
}