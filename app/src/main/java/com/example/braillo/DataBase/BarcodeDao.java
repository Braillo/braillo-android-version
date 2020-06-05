package com.example.braillo.DataBase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BarcodeDao {

    @Query("SELECT * FROM barcodes where ean = :barcodeNumber")
    List<Barcode> loadFullName(String barcodeNumber);
}
