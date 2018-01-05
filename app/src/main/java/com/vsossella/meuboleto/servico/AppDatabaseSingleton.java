package com.vsossella.meuboleto.servico;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by vsossella on 27/05/17.
 */

public class AppDatabaseSingleton {

    private static AppDatabase db;

    public static AppDatabase instanceDb(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "database-meuboleto").build();
        }
        return db;
    }
}
