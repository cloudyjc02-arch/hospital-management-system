package project;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHelper {

    // ─── Authentication ──────────────────────────────────────────────────────

    public static AuthResult authenticateUser(String username, String plainPassword) {
        String sql = "SELECT password, role FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String roleStr        = rs.getString("role");

                if (plainPassword.equals(storedPassword)) {
                    UserRole role = UserRole.valueOf(roleStr.toUpperCase());
                    return AuthResult.ok(username.trim(), role);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return AuthResult.fail();
    }

    // ─── Data Retrieval ───────────────────────────────────────────────────────

    public static ObservableList<Patient> getPatients() {
        ObservableList<Patient> list = FXCollections.observableArrayList();
        String sql = "SELECT `Patient ID`, `Name`, `Age`, `Condition`, `Last Name` FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Patient(
                    rs.getInt("Patient ID"),
                    rs.getString("Name"),
                    rs.getInt("Age"),
                    rs.getString("Condition"),
                    rs.getString("Last Name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Employee> getEmployees() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        String sql = "SELECT `Employee ID`, `Full Name`, `Wage (per hour)`, `Work Hours`, `Position` FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Employee(
                    rs.getInt("Employee ID"),
                    rs.getString("Full Name"),
                    rs.getInt("Wage (per hour)"),
                    rs.getInt("Work Hours"),
                    rs.getString("Position")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Stats> getStats() {
        ObservableList<Stats> list = FXCollections.observableArrayList();
        String sql = "SELECT `Statistics`, `Value`, `Change (Last Month)`, `Comments` FROM stats";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Stats(
                    rs.getString("Statistics"),
                    rs.getInt("Value"),
                    rs.getInt("Change (Last Month)"),
                    rs.getString("Comments")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Details> getDetails() {
        ObservableList<Details> list = FXCollections.observableArrayList();
        String sql = "SELECT `Item`, `Details` FROM details";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Details(
                    rs.getString("Item"),
                    rs.getString("Details")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}