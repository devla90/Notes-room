package com.luisrosales.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.luisrosales.notes.db.NotaRoomDatabase;
import com.luisrosales.notes.db.dao.NotaDao;
import com.luisrosales.notes.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {

    private NotaDao notaDao;

    public NotaRepository(Application application){

        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);

        //Obtenemos una instancia del notadao
        notaDao = db.notaDao();
    }

    //Declaramos todas las operaciones que hará el repositorio
    //El repositorio delegará todas las operaciones sobre el notadao
    // Aquí tbn se implementaría las sesiones para una api

    public LiveData<List<NotaEntity>> getAll(){
        return notaDao.getAll();
    }

    public LiveData<List<NotaEntity>> getAllFavs(){
        return notaDao.getAllFavoritos();
    }

    public void insert(NotaEntity nota){
        //Las consultas se hacen de forma asyncrona(creamos la clase)
        new insertAsyncTask(notaDao).execute(nota);

    }

    public void update(NotaEntity nota){
        //Las consultas se hacen de forma asyncrona(creamos la clase)
        new updateAsyncTask(notaDao).execute(nota);

    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void,Void>{
        private NotaDao notaDaoAsyncTask;

        insertAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {

            //Solo insertamos el primer elemento de ese array
            notaDaoAsyncTask.insertNota(notaEntities[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<NotaEntity, Void,Void>{
        private NotaDao notaDaoAsyncTask;

        updateAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {

            //Solo insertamos el primer elemento de ese array
            notaDaoAsyncTask.updateNota(notaEntities[0]);
            return null;
        }
    }

}
