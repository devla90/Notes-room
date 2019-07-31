package com.luisrosales.notes.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.luisrosales.notes.db.dao.NotaDao;
import com.luisrosales.notes.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {
    public abstract NotaDao notaDao();

    //variable que guarda la instancia de la BD(estatica y volatil)
    private static volatile NotaRoomDatabase INSTANCE;

    //Obtendremos la instancia de la BD de diferentes puntos del código, se declara un contexto
    public static NotaRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database").build();
                }
            }
        }
        return INSTANCE;
    }


}
