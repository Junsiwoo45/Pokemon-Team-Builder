package com.example.pokemonteamproject.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    private int CID;

    private int id;

    private String team_name;

    private String name;




    //ALT INSERT TO CHANGE THESE


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokemon(int id, String team_name, String name) {

        this.id = id;
        this.team_name = team_name;
        this.name = name;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }


    @Override
    public String toString() {
        return "Pokemon{" +
                "CID=" + CID +
                ", id=" + id +
                ", team_name='" + team_name + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
