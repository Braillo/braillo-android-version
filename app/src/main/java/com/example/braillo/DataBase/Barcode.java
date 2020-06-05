package com.example.braillo.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barcodes")
public class Barcode {
    @ColumnInfo(name = "ean")
    public String barcodeNumber;
    @ColumnInfo(name = "name")
    public String barcodeName;
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    public Barcode(String barcodeNumber, String barcodeName) {
        this.barcodeNumber = barcodeNumber;
        this.barcodeName = barcodeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public String getBarcodeName() {
        return barcodeName;
    }
}
