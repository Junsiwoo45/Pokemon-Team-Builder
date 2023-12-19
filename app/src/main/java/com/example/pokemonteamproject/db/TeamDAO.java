package com.example.pokemonteamproject.db;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TeamDAO {

    @Query("select * from Team")
    LiveData<List<Team>> getAll();


    @Insert
    void InsertList(ArrayList<Team> team);

    @Query("select * from Team where CID = :cid")
    Team getByID(int cid);

    @Update
    void update(Team team);

    @Delete
    void delete(Team team);

    @Insert
    void insert(Team... team);

}
