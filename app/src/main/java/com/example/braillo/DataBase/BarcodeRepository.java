package com.example.braillo.DataBase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BarcodeRepository {
    private BarcodeDao barcodeDao;
    private List<Barcode> nameCode;

    public BarcodeRepository(Application application) {
        BarcodeDB barcodeDB = BarcodeDB.getInstance(application);
        barcodeDao = barcodeDB.barcodeDao();


    }

    public List<Barcode> getNameCode(String barcode) throws ExecutionException, InterruptedException {
        return new BarcodeNames(barcodeDao).execute(barcode).get();
    }

    private static class BarcodeNames extends AsyncTask<String, Void, List<Barcode>> {

        private BarcodeDao barcodeDao;

        public BarcodeNames(BarcodeDao barcodeDao) {
            this.barcodeDao = barcodeDao;
        }

        @Override
        protected List<Barcode> doInBackground(String... strings) {
            return barcodeDao.loadFullName(strings[0]);

        }
    }

}
