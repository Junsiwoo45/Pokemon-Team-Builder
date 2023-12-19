package com.example.pokemonteamproject;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class PokemonViewModel extends ViewModel {
    private ArrayList<String> savedPokemonNames = new ArrayList<>();
    private ArrayList<String> savedPokemonNumber = new ArrayList<>();
    private static final int MAX_TEAM_SIZE = 6;

    private int pokemonCounter = 0;

    public ArrayList<String> getSavedPokemonNames() {
        return savedPokemonNames;
    }
    public ArrayList<String> getSavedPokemonNumber() {
        return savedPokemonNumber;
    }

    public boolean canAddPokemonToTeam() {
        return savedPokemonNames.size() < MAX_TEAM_SIZE;
    }
    public void addPokemonNumber(String number){
        savedPokemonNumber.add(number);
    }
    public void addPokemonName(String name) {
        if (canAddPokemonToTeam()) {
            savedPokemonNames.add(name);

        }

    }
    public void resetSavedPokemonData() {
        savedPokemonNames.clear();
        savedPokemonNumber.clear();
    }

    public int getPokemonCounter() {
        return pokemonCounter;
    }

    public void setPokemonCounter(int counter) {

        pokemonCounter += counter;
    }
    public void clearData() {
        resetSavedPokemonData();
        //resetPokemonCounter();
    }

    public void resetPokemonCounter() {
        pokemonCounter = 0;
    }
}
