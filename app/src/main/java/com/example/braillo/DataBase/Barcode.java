package com.example.braillo.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barcodes")
public class Barcode {
    public void setId(int id) {
        this.id = id;
    }
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "ean")
    public String barcodeNumber;
    @ColumnInfo(name = "name")
    public String barcodeName ;

    public Barcode(String barcodeNumber, String barcodeName) {
        this.barcodeNumber = barcodeNumber;
        this.barcodeName = barcodeName;
    }

    public int getId() {
        return id;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public String getBarcodeName() {
        return barcodeName;
    }
}
