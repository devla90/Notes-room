package com.luisrosales.notes.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.luisrosales.notes.db.entity.NotaEntity;

import java.util.List;

@Dao
public interface NotaDao {

    @Insert
    void insertNota(NotaEntity nota);

    @Update
    void updateNota(NotaEntity nota);

    @Query("DELETE FROM notas")
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :idNota")
    void deleteById(int idNota);

    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    LiveData<List<NotaEntity>> getAll();

    @Query("SELECT * FROM notas WHERE favorito LIKE 'true'")
    LiveData<List<NotaEntity>> getAllFavoritos();

}
