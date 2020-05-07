package com.example.menuv01;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EntradasRepository {
    private EntradasDao mEntradasDao;
    private LiveData<List<Entradas>> mAllEntradas;

    public EntradasRepository(Application application) {                   //Class to use repository in other parts
        MenuRoomDatabase db = MenuRoomDatabase.getDatabase(application);
        mEntradasDao = db.entradasDao();
        mAllEntradas = mEntradasDao.getAllEntradas();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Entradas>> getAllEntradas() {
        return mAllEntradas;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertEntrada(Entradas entradas) {
        //     new InsertEntradaAsyncTask(mEntradasDao).execute(entradas);               //Insert entradas using the DAO
        MenuRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEntradasDao.insertEntrada(entradas);
        });
    }

    public void updateEntrada(Entradas entradas) {
        new UpdateEntradaAsyncTask(mEntradasDao).execute(entradas);

    }

    public void deleteEntrada(Entradas entradas) {
        new DeleteEntradaAsyncTask(mEntradasDao).execute(entradas);

    }

    public void deleteAllEntradas(Entradas entradas) {
        new DeleteAllEntradaAsyncTask(mEntradasDao).execute(entradas);

    }

    private static class InsertEntradaAsyncTask extends AsyncTask<Entradas, Void, Void> {        //Insert entradas using the DAO Async, need further testing
        private EntradasDao mEntradasDao;

        private InsertEntradaAsyncTask(EntradasDao mEntradasDao) {
            this.mEntradasDao = mEntradasDao;
        }

        @Override
        protected Void doInBackground(Entradas... entradas) {
            mEntradasDao.insertEntrada(entradas[0]);
            return null;
        }
    }

    private static class UpdateEntradaAsyncTask extends AsyncTask<Entradas, Void, Void> {
        private EntradasDao mEntradasDao;

        private UpdateEntradaAsyncTask(EntradasDao mEntradasDao) {
            this.mEntradasDao = mEntradasDao;
        }

        @Override
        protected Void doInBackground(Entradas... entradas) {
            mEntradasDao.updateEntrada(entradas[0]);
            return null;
        }
    }

    private static class DeleteEntradaAsyncTask extends AsyncTask<Entradas, Void, Void> {
        private EntradasDao mEntradasDao;

        private DeleteEntradaAsyncTask(EntradasDao mEntradasDao) {
            this.mEntradasDao = mEntradasDao;
        }

        @Override
        protected Void doInBackground(Entradas... entradas) {
            mEntradasDao.deleteEntrada(entradas[0]);
            return null;
        }
    }

    private static class DeleteAllEntradaAsyncTask extends AsyncTask<Entradas, Void, Void> {
        private EntradasDao mEntradasDao;

        private DeleteAllEntradaAsyncTask(EntradasDao mEntradasDao) {
            this.mEntradasDao = mEntradasDao;
        }

        @Override
        protected Void doInBackground(Entradas... entradas) {
            mEntradasDao.deleteEntrada(entradas[0]);
            return null;
        }
    }
}
