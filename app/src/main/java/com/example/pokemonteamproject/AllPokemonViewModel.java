package com.example.pokemonteamproject;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;

import java.util.List;

public class AllPokemonViewModel extends ViewModel {
    private LiveData<List<Pokemon>> pokemonList;

    public LiveData<List<Pokemon>> getPokemonList(Context c){
        if(pokemonList != null){
            return pokemonList;
        }
        else{
            return pokemonList = AppDatabase.getInstance(c).pokemonDAO().getAll();
        }
    }
}
