package com.mycompany.simpus.models;

import javafx.beans.property.*;

public class Patient {
    private IntegerProperty idPasien;
    private StringProperty namaPasien;
    private StringProperty nik;
    private StringProperty jenisKelamin;
    private StringProperty insuransi;

    public Patient(int idPasien, String namaPasien, String nik, String jenisKelamin, String insuransi) {
        this.idPasien = new SimpleIntegerProperty(idPasien);
        this.namaPasien = new SimpleStringProperty(namaPasien);
        this.nik = new SimpleStringProperty(nik);
        this.jenisKelamin = new SimpleStringProperty(jenisKelamin);
        this.insuransi = new SimpleStringProperty(insuransi);
    }

    // Getters for properties
    public IntegerProperty idPasienProperty() {
        return idPasien;
    }

    public StringProperty namaPasienProperty() {
        return namaPasien;
    }

    public StringProperty nikProperty() {
        return nik;
    }

    public StringProperty jenisKelaminProperty() {
        return jenisKelamin;
    }

    public StringProperty insuransiProperty() {
        return insuransi;
    }

    // Traditional getters and setters
    public int getIdPasien() {
        return idPasien.get();
    }

    public void setIdPasien(int idPasien) {
        this.idPasien.set(idPasien);
    }

    public String getNamaPasien() {
        return namaPasien.get();
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien.set(namaPasien);
    }

    public String getNik() {
        return nik.get();
    }

    public void setNik(String nik) {
        this.nik.set(nik);
    }

    public String getJenisKelamin() {
        return jenisKelamin.get();
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin.set(jenisKelamin);
    }

    public String getInsuransi() {
        return insuransi.get();
    }

    public void setInsuransi(String insuransi) {
        this.insuransi.set(insuransi);
    }
}
