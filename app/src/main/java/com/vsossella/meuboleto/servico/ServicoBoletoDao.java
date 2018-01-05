package com.vsossella.meuboleto.servico;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by vsossella on 27/05/17.
 */

@Dao
public interface ServicoBoletoDao {

    @Query("SELECT * FROM codigodebarra")
    Flowable<List<CodigoDeBarra>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CodigoDeBarra codigoDeBarra);

    @Delete
    void delete(CodigoDeBarra codigoDeBarra);

    @Update
    void update(CodigoDeBarra codigoDeBarra);

    @Query("SELECT * FROM codigodebarra WHERE codigodebarras == :codigoDeBarras")
    List<CodigoDeBarra> loadByCodigoDeBarras(String codigoDeBarras);



}
