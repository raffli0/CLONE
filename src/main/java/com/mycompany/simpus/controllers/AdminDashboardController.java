package com.mycompany.simpus.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.simpus.DatabaseConnection;
import com.mycompany.simpus.models.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminDashboardController {
    // menu sidebar start
    @FXML
    private Button btnDataPasien;
    @FXML
    private Button btnListRegistrasi;
    @FXML
    private Button btnRekamMedis;
    @FXML
    private Button btnDataPetugas;
    @FXML
    private Button btnLogout;
    // menu sidebar end

    // isi dashboard start
    @FXML
    private StackPane contentArea;
    @FXML
    private VBox dashboardPane; // Pane untuk Dashboard
    // isi dashboard end

    // tombol
    @FXML
    private VBox rootContainer;
    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    public void initialize() {
        // Set up button actions
        btnDataPasien.setOnAction(event -> {
            loadView("data_pasien.fxml");
            setActiveMenu(btnDataPasien); // Set active menu
        });
        btnListRegistrasi.setOnAction(event -> {
            loadView("list_registrasi.fxml");
            setActiveMenu(btnListRegistrasi); // Set active menu
        });
        btnRekamMedis.setOnAction(event -> {
            loadView("rekam_medis.fxml");
            setActiveMenu(btnRekamMedis); // Set active menu
        });
        btnDataPetugas.setOnAction(event -> {
            loadView("data_petugas.fxml");
            setActiveMenu(btnDataPetugas); // Set active menu
        });
        btnLogout.setOnAction(event -> logout());

        // Default: Tampilkan Dashboard
        showDashboard();

    }

    // Menampilkan Dashboard Pane
    private void showDashboard() {
        dashboardPane.setVisible(true);
        dashboardPane.setManaged(true);
    }


    private void setActiveMenu(Button activeButton) {
        // Reset semua tombol ke gaya default
        btnDataPasien.getStyleClass().remove("menu-button-active");
        btnListRegistrasi.getStyleClass().remove("menu-button-active");
        btnRekamMedis.getStyleClass().remove("menu-button-active");
        btnDataPetugas.getStyleClass().remove("menu-button-active");

        // Tambahkan gaya "active" ke tombol yang dipilih
        activeButton.getStyleClass().add("menu-button-active");
    }

    void loadView(String fxmlFile) {
        try {
            // Load and replace content in the contentArea
            javafx.scene.Parent view = javafx.fxml.FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFile));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String logout() {
        System.out.println("Logging out...");
        // Redirect to the login page or close the application
        try {
            // Muat halaman login (login.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login_page.fxml"));
            Scene loginScene = new Scene(loader.load());

            // Ambil stage saat ini dan set scene login
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(loginScene);

            // Menampilkan kembali jendela login
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Tampilkan pesan error jika gagal memuat halaman login
        }
        return null;
    }
}
