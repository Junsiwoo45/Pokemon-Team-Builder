package com.example.pokemonteamproject.db;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int CID;

    private String pokemonTeamName;

    private ArrayList<String> PokemonNumber;

    private ArrayList<String> listPokemon;


    public Team( String pokemonTeamName, ArrayList<String> PokemonNumber, ArrayList<String> listPokemon) {
        this.pokemonTeamName = pokemonTeamName;
        this.PokemonNumber = PokemonNumber;
        this.listPokemon = listPokemon;
    }

    @Override
    public String toString() {
        return "Team{" +
                "CID=" + CID +
                ", pokemonTeamName='" + pokemonTeamName + '\'' +
                ", PokemonNumber=" + PokemonNumber +
               ", listPokemon=" + listPokemon +
                '}';
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getPokemonTeamName() {
        return pokemonTeamName;
    }

    public void setPokemonTeamName(String pokemonTeamName) {
        this.pokemonTeamName = pokemonTeamName;
    }

    public ArrayList<String> getPokemonNumber() {
        return PokemonNumber;
    }

    public void setPokemonNumber(ArrayList<String> PokemonNumber) {
        this.PokemonNumber = PokemonNumber;
    }

    public ArrayList<String> getListPokemon() {
        return listPokemon;
    }

    public void setListPokemon(ArrayList<String> listPokemon) {
        this.listPokemon = listPokemon;
    }
}
