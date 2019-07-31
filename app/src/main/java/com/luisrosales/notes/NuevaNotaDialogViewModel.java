package com.luisrosales.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.luisrosales.notes.db.entity.NotaEntity;

import java.util.List;

//Clase mediadora para transferir datos de un fragmento a Otro
public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;


    public NuevaNotaDialogViewModel(Application application){
        super(application);

        //Instanciando el repositorio
        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    //El fragment necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas(){
        return allNotas;
    }

    //El fragmento que inserte una nueva nota, deberá comunicarlo a este Viewmodel
    public void insertarNota(NotaEntity nuevaNotaEntity){
        notaRepository.insert(nuevaNotaEntity);
    }

    public void updateNota(NotaEntity actualizarNotaEntity){
        notaRepository.update(actualizarNotaEntity);
    }


}
