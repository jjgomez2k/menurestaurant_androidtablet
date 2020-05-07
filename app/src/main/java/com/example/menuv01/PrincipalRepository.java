package com.example.menuv01;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PrincipalRepository {
    private PrincipalDao mPrincipalDao;
    private LiveData<List<Principal>> mAllPrincipal;

    public PrincipalRepository(Application application) {                   //Class to use repository in other parts
        MenuRoomDatabase db = MenuRoomDatabase.getDatabase(application);
        mPrincipalDao = db.principalDao();
        mAllPrincipal = mPrincipalDao.getAllPrincipal();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Principal>> getAllPrincipal() {
        return mAllPrincipal;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertPrincipal(Principal principal) {
        //     new InsertEntradaAsyncTask(mEntradasDao).execute(entradas);               //Insert entradas using the DAO
        MenuRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPrincipalDao.insertPrincipal(principal);
        });
    }

    public void updatePrincipal(Principal principal) {
        new UpdatePrincipalAsyncTask(mPrincipalDao).execute(principal);

    }

    public void deletePrincipal(Principal principal) {
        new DeletePrincipalAsyncTask(mPrincipalDao).execute(principal);

    }

    public void deleteAllPrincipal(Principal principal) {
        new DeleteAllPrincipalAsyncTask(mPrincipalDao).execute(principal);

    }

    private static class InsertPrincipalAsyncTask extends AsyncTask<Principal, Void, Void> {        //Insert entradas using the DAO Async, need further testing
        private PrincipalDao mPrincipalDao;

        private InsertPrincipalAsyncTask(PrincipalDao mPrincipalDao) {
            this.mPrincipalDao = mPrincipalDao;
        }

        @Override
        protected Void doInBackground(Principal... principal) {
            mPrincipalDao.insertPrincipal(principal[0]);
            return null;
        }
    }

    private static class UpdatePrincipalAsyncTask extends AsyncTask<Principal, Void, Void> {
        private PrincipalDao mPrincipalDao;

        private UpdatePrincipalAsyncTask(PrincipalDao mPrincipalDao) {
            this.mPrincipalDao = mPrincipalDao;
        }

        @Override
        protected Void doInBackground(Principal... principal) {
            mPrincipalDao.updatePrincipal(principal[0]);
            return null;
        }
    }

    private static class DeletePrincipalAsyncTask extends AsyncTask<Principal, Void, Void> {
        private PrincipalDao mPrincipalDao;

        private DeletePrincipalAsyncTask(PrincipalDao mPrincipalDao) {
            this.mPrincipalDao = mPrincipalDao;
        }

        @Override
        protected Void doInBackground(Principal... principal) {
            mPrincipalDao.deletePrincipal(principal[0]);
            return null;
        }
    }

    private static class DeleteAllPrincipalAsyncTask extends AsyncTask<Principal, Void, Void> {
        private PrincipalDao mPrincipalDao;

        private DeleteAllPrincipalAsyncTask(PrincipalDao mPrincipalDao) {
            this.mPrincipalDao = mPrincipalDao;
        }

        @Override
        protected Void doInBackground(Principal... principal) {
            mPrincipalDao.deletePrincipal(principal[0]);
            return null;
        }
    }
}
