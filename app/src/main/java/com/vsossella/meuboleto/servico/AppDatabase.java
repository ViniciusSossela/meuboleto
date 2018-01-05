package com.vsossella.meuboleto.servico;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;

/**
 * Created by vsossella on 27/05/17.
 */

@Database(entities = {CodigoDeBarra.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ServicoBoletoDao ServicoBoletoDao();
}

