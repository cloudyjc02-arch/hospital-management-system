package project;

import javafx.beans.property.*;  // Import JavaFX property classes

public class Stats {

    private final StringProperty statistics;  // Property for the statistics description
    private final IntegerProperty value;  // Property for the value of the statistics
    private final IntegerProperty changeLastMonth;  // Property for the change in statistics from last month
    private final StringProperty comments;  // Property for additional comments

    // Constructor to initialize all fields
    public Stats(String statistics, int value, int changeLastMonth, String comments) {
        this.statistics = new SimpleStringProperty(statistics);  // Initialize statistics property
        this.value = new SimpleIntegerProperty(value);  // Initialize value property
        this.changeLastMonth = new SimpleIntegerProperty(changeLastMonth);  // Initialize changeLastMonth property
        this.comments = new SimpleStringProperty(comments);  // Initialize comments property
    }

    // Getters and Property Methods
    public String getStatistics() {
        return statistics.get();  // Return statistics description
    }

    public void setStatistics(String statistics) {
        this.statistics.set(statistics);  // Set the value of statistics property
    }

    public StringProperty statisticsProperty() {
        return statistics;  // Return property for statistics
    }

    public int getValue() {
        return value.get();  // Return the value of the statistics
    }

    public void setValue(int value) {
        this.value.set(value);  // Set the value of the value property
    }

    public IntegerProperty valueProperty() {
        return value;  // Return property for value
    }

    public int getChangeLastMonth() {
        return changeLastMonth.get();  // Return the change in statistics from last month
    }

    public void setChangeLastMonth(int changeLastMonth) {
        this.changeLastMonth.set(changeLastMonth);  // Set the value of changeLastMonth property
    }

    public IntegerProperty changeLastMonthProperty() {
        return changeLastMonth;  // Return property for changeLastMonth
    }

    public String getComments() {
        return comments.get();  // Return additional comments
    }

    public void setComments(String comments) {
        this.comments.set(comments);  // Set the value of comments property
    }

    public StringProperty commentsProperty() {
        return comments;  // Return property for comments
    }
}