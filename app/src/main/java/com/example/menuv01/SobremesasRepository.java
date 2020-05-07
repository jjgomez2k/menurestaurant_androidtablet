package com.example.menuv01;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SobremesasRepository {
    private SobremesasDao mSobremesasDao;
    private LiveData<List<Sobremesas>> mAllSobremesas;

    public SobremesasRepository(Application application) {                   //Class to use repository in other parts
        MenuRoomDatabase db = MenuRoomDatabase.getDatabase(application);
        mSobremesasDao = db.sobremesasDao();
        mAllSobremesas = mSobremesasDao.getAllSobremesas();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Sobremesas>> getAllSobremesas() {
        return mAllSobremesas;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertSobremesas(Sobremesas sobremesas) {
        //     new InsertEntradaAsyncTask(mEntradasDao).execute(entradas);               //Insert entradas using the DAO
        MenuRoomDatabase.databaseWriteExecutor.execute(() -> {
            mSobremesasDao.insertSobremesas(sobremesas);
        });
    }

    public void updateSobremesas(Sobremesas sobremesas) {
        new SobremesasRepository.UpdateSobremesasAsyncTask(mSobremesasDao).execute(sobremesas);

    }

    public void deleteSobremesas(Sobremesas sobremesas) {
        new SobremesasRepository.DeleteSobremesasAsyncTask(mSobremesasDao).execute(sobremesas);

    }

    public void deleteAllSobremesas(Sobremesas sobremesas) {
        new SobremesasRepository.DeleteAllSobremesasAsyncTask(mSobremesasDao).execute(sobremesas);

    }

    private static class InsertPrincipalAsyncTask extends AsyncTask<Sobremesas, Void, Void> {        //Insert entradas using the DAO Async, need further testing
        private SobremesasDao mSobremesasDao;

        private InsertPrincipalAsyncTask(SobremesasDao mSobremesasDao) {
            this.mSobremesasDao = mSobremesasDao;
        }

        @Override
        protected Void doInBackground(Sobremesas... sobremesas) {
            mSobremesasDao.insertSobremesas(sobremesas[0]);
            return null;
        }
    }

    private static class UpdateSobremesasAsyncTask extends AsyncTask<Sobremesas, Void, Void> {
        private SobremesasDao mSobremesasDao;

        private UpdateSobremesasAsyncTask(SobremesasDao mSobremesasDao) {
            this.mSobremesasDao = mSobremesasDao;
        }

        @Override
        protected Void doInBackground(Sobremesas... sobremesas) {
            mSobremesasDao.updateSobremesas(sobremesas[0]);
            return null;
        }
    }

    private static class DeleteSobremesasAsyncTask extends AsyncTask<Sobremesas, Void, Void> {
        private SobremesasDao mSobremesasDao;

        private DeleteSobremesasAsyncTask(SobremesasDao mSobremesasDao) {
            this.mSobremesasDao = mSobremesasDao;
        }

        @Override
        protected Void doInBackground(Sobremesas... sobremesas) {
            mSobremesasDao.deleteSobremesas(sobremesas[0]);
            return null;
        }
    }

    private static class DeleteAllSobremesasAsyncTask extends AsyncTask<Sobremesas, Void, Void> {
        private SobremesasDao mSobremesasDao;

        private DeleteAllSobremesasAsyncTask(SobremesasDao mSobremesasDao) {
            this.mSobremesasDao = mSobremesasDao;
        }

        @Override
        protected Void doInBackground(Sobremesas... sobremesas) {
            mSobremesasDao.deleteSobremesas(sobremesas[0]);
            return null;
        }
    }
}
