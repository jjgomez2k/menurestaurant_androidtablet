package com.example.menuv01;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Entradas.class, Principal.class, Sobremesas.class}, version = 9, exportSchema = false)
public abstract class MenuRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static MenuRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //If you want to keep data through app restarts, comment out the following block
            databaseWriteExecutor.execute(() -> {
                //Populate the database in the background, if you want to start with more add them
                EntradasDao entradasdao = INSTANCE.entradasDao();
                entradasdao.deleteAllEntradas();
                Entradas entrada1 = new Entradas(1, "Entrada numero 1", "Entrada numero 1 detalhe", 29.99); //Prepopulate the DB
                entradasdao.insertEntrada(entrada1);
                Entradas entrada2 = new Entradas(2, "Entrada numero 2", "Entrada numero 2 detalhe", 18.90);
                entradasdao.insertEntrada(entrada2);

                PrincipalDao principaldao = INSTANCE.principalDao();
                principaldao.deleteAllPrincipal();
                Principal principal1 = new Principal(1, "Principal numero 1", "Principal numero 1 detalhe", 149.90); //Prepopulate the DB
                principaldao.insertPrincipal(principal1);
                Principal principal2 = new Principal(2, "Principal numero 2", "Principal numero 2 detalhe", 219.90);
                principaldao.insertPrincipal(principal2);

                SobremesasDao sobremesasdao = INSTANCE.sobremesasDao();
                sobremesasdao.deleteAllSobremesas();
                Sobremesas sobremesas1 = new Sobremesas(1, "Sobremesa numero 1", "Sobremesa numero 1 detalhe", 23.99); //Prepopulate the DB
                sobremesasdao.insertSobremesas(sobremesas1);
                Sobremesas sobremesas2 = new Sobremesas(2, "Sobremesa numero 2", "Sobremesa numero 2 detalhe", 9.90);
                sobremesasdao.insertSobremesas(sobremesas2);
                Sobremesas sobremesas3 = new Sobremesas(3, "デザート 一", "デザート　一　イチゴのアイスクリーム ", 3.99); //Prepopulate the DB
                sobremesasdao.insertSobremesas(sobremesas3);
            });
        }
    };

    static MenuRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MenuRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MenuRoomDatabase.class, "menu_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract EntradasDao entradasDao();

    public abstract PrincipalDao principalDao();

    public abstract SobremesasDao sobremesasDao();
}


