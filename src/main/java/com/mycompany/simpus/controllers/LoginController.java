///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//package com.mycompany.simpus.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
//import java.io.IOException;
//
//public class LoginController {
//
//    @FXML
//    private TextField txtUsername;
//    @FXML
//    private PasswordField txtPassword;
//    @FXML
//    private ComboBox<String> cmbRole;
//    @FXML
//    private Button btnLogin;
//    @FXML
//    private Label lblError;
//
//    public void initialize() {
//        // Initialize button action
//        btnLogin.setOnAction(event -> handleLogin());
//    }
//
//    private void handleLogin() {
//        String username = txtUsername.getText();
//        String password = txtPassword.getText();
//        String role = cmbRole.getValue();
//
//        // Validate input
//        if (username.isEmpty() || password.isEmpty() || role == null) {
//            lblError.setText("All fields must be filled!");
//            return;
//        }
//
//        // Dummy authentication logic (replace with actual DB query)
//        if (authenticate(username, password, role)) {
//            lblError.setText(""); // Clear error message
//
//            try {
//                String dashboardFile;
//                switch (role) {
//                    case "Admin":
//                        dashboardFile = "/fxml/admin_dashboard.fxml";
//                        break;
//                    case "Doctor":
//                        dashboardFile = "/fxml/doctor_dashboard.fxml";
//                        break;
//                    case "Pharmacist":
//                        dashboardFile = "/fxml/pharmacist_dashboard.fxml";
//                        break;
//                    default:
//                        dashboardFile = null;
//                        break;
//                }
//
//                if (dashboardFile != null) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardFile));
//                    Scene scene = new Scene(loader.load());
//                    Stage stage = (Stage) btnLogin.getScene().getWindow();
//                    stage.setScene(scene);
//                } else {
//                    lblError.setText("Invalid role!"); // Display error if role is not valid
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            lblError.setText("Invalid username, password, or role!");
//        }
//    }
//
//    private boolean authenticate(String username, String password, String role) {
//        // Replace this logic with actual database authentication
//        return username.equals("admin") && password.equals("admin123") && role.equals("admin");
//    }
//}
//

package com.mycompany.simpus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.*;
import javafx.scene.layout.VBox;
import com.mycompany.simpus.DatabaseConnection;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cmbRole;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblError;
    @FXML
    private VBox rootContainer;

    public void initialize() {
        // Tambahkan item ke ComboBox tanpa duplikasi
        if (cmbRole.getItems().isEmpty()) {
            cmbRole.getItems().addAll("Admin", "Dokter", "Apoteker");
        }

        // Set login button action
        btnLogin.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String role = cmbRole.getValue();

        // Validasi input
        if (username.isEmpty() || password.isEmpty() || role == null) {
            lblError.setText("All fields must be filled!");
            return;
        }

        // Autentikasi pengguna
        if (authenticate(username, password, role)) {
            lblError.setText(""); // Clear error message

            try {
                String dashboardFile = getDashboardFileByRole(role);
                if (dashboardFile != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardFile));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(scene);
                } else {
                    lblError.setText("Invalid role!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblError.setText("Invalid username, password, or role!");
        }
    }

    private boolean authenticate(String username, String password, String role) {
        // DB connection and authentication query
        String query = "SELECT u.id, r.name " +
                "FROM Users u " +
                "JOIN Roles r ON u.role_id = r.id " +
                "WHERE u.username = ? AND u.password = ? AND r.name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            // Encrypt password check here, for simplicity, assume plain text
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Return true if user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Autentikasi gagal
    }

    private String getDashboardFileByRole(String role) {
        switch (role) {
            case "Admin":
                return "/fxml/admin_dashboard.fxml";
            case "Dokter":
                return "/fxml/dokter_dashboard.fxml";
            case "Apoteker":
                return "/fxml/apoteker_dashboard.fxml";
            default:
                return null;
        }
    }
}
