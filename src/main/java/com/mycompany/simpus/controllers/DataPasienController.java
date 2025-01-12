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

public class DataPasienController {
    // isi dashboard start
    @FXML
    private VBox contentArea;
    // isi dashboard end

    // tombol
    @FXML
    private Button btnTambahPasien;
    @FXML
    private VBox rootContainer;

    @FXML
    public void initialize() {
        if (contentArea == null) {
            System.out.println("contentArea is null");
        }

        // Set up button actions
        btnTambahPasien.setOnAction(event -> loadView("tambah_pasien.fxml"));
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

}

