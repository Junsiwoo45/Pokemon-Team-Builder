package com.example.pokemonteamproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.ArrayList;
@Dao
public interface PokemonDAO {

    @Query("select * from Pokemon")
    LiveData<List<Pokemon>> getAll();


    @Insert
    void InsertList(ArrayList<Pokemon> pokemon);

    @Query("select * from Pokemon where CID = :cid")
    Pokemon getByID(int cid);

    @Update
    void update(Pokemon pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Insert
    void insert(Pokemon... pokemon);

}
