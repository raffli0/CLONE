package com.mycompany.simpus.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TambahPasienController {

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtJenisKelamin;
    @FXML
    private DatePicker dpTanggalLahir;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnBatal;

    private boolean dataSaved = false;

    @FXML
    public void initialize() {
        btnBatal.setOnAction(event -> redirectToDataPasien());
        btnSimpan.setOnAction(event -> saveData());
    }

    private void redirectToDataPasien() {
        System.out.println("Redirecting to Data Pasien...");
        try {
            // Load the Data Pasien page (data_pasien.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_dashboard.fxml"));
            Scene dataPasienScene = new Scene(loader.load());

            // Get the current stage and set the Data Pasien scene
            Stage stage = (Stage) btnBatal.getScene().getWindow();
            stage.setScene(dataPasienScene);
            // Show the Data Pasien page
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Data Pasien page.");
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) btnBatal.getScene().getWindow();
        stage.close();
    }

    private void saveData() {
        if (txtNama.getText().isEmpty() || txtJenisKelamin.getText().isEmpty() || dpTanggalLahir.getValue() == null) {
            showAlert("Error", "Semua field harus diisi.");
            return;
        }

        dataSaved = true;
        closeDialog();
    }

    

    public boolean isDataSaved() {
        return dataSaved;
    }

    public String getNama() {
        return txtNama.getText();
    }

    public String getJenisKelamin() {
        return txtJenisKelamin.getText();
    }

    public String getTanggalLahir() {
        return dpTanggalLahir.getValue().toString();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

