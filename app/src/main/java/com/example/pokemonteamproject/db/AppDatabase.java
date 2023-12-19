package com.example.pokemonteamproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Pokemon.class, Team.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance !=null){
            return instance;
        }else{
            instance = Room.databaseBuilder(context, AppDatabase.class, "user-database").build();
            return instance;
        }
    }

    public abstract PokemonDAO pokemonDAO();
    public abstract TeamDAO teamDAO();
}

