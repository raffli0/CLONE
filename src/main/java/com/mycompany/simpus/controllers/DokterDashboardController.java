package com.mycompany.simpus.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DokterDashboardController {

    @FXML
    private Button btnLogout;
    @FXML
    private Button btnPemeriksaan;
    @FXML
    private Button btnRiwayatResep;
    @FXML
    private StackPane contentArea;
    @FXML
    private VBox dashboardPane; // Pane untuk Dashboard
    @FXML
    private VBox pemeriksaanPane; // Pane untuk Menu Pemeriksaan
    @FXML
    private VBox riwayatResepPane; // Pane untuk Menu RiwayatResep

    public void initialize() {
        // Set up button actions
        btnPemeriksaan.setOnAction(event -> loadView("pemeriksaan.fxml"));
        btnRiwayatResep.setOnAction(event -> loadView("riwayat_resep.fxml"));
        btnLogout.setOnAction(event -> logout());

        // Default: Tampilkan Dashboard
        showDashboard();

        // Event: Tombol Data Pasien
        btnPemeriksaan.setOnAction(event -> {
            showPemeriksaan();
            setActiveMenu(btnPemeriksaan);
        });

        // Event untuk tombol List Registrasi
        btnRiwayatResep.setOnAction(event -> {
            setActiveMenu(btnRiwayatResep);
        });
    }

    // Menampilkan Dashboard Pane
    private void showDashboard() {
        dashboardPane.setVisible(true);
        dashboardPane.setManaged(true);
    }

    private void showPemeriksaan() {
        pemeriksaanPane.setVisible(true);
        pemeriksaanPane.setManaged(true);

        dashboardPane.setVisible(false);
        dashboardPane.setManaged(false);
    }

    private void setActiveMenu(Button activeButton) {
        // Reset semua tombol ke gaya default
        btnPemeriksaan.getStyleClass().remove("menu-button-active");
        btnRiwayatResep.getStyleClass().remove("menu-button-active");

        // Tambahkan gaya "active" ke tombol yang dipilih
        activeButton.getStyleClass().add("menu-button-active");
    }

    private void loadView(String fxmlFile) {
        try {
            // Load and replace content in the contentArea
            javafx.scene.Parent view = javafx.fxml.FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFile));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout() {
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
    }
}
