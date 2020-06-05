package com.example.braillo.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Barcode.class}, version = 1)
public abstract class BarcodeDB extends RoomDatabase {
    private static BarcodeDB instance;

    public static synchronized BarcodeDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BarcodeDB.class, "barcode")
                    .createFromAsset("DataBase/barcodes.db")
                    .build();
        }
        return instance;
    }

    public abstract BarcodeDao barcodeDao();


}
