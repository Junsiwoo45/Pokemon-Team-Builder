package com.example.pokemonteamproject;

import static androidx.lifecycle.LiveDataKt.observe;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class PokemonRecycler extends Fragment{

LiveData<List<Pokemon>> tempPokemon;
View view;
LiveData<List<Pokemon>> pokemon;


private RecyclerView recyclerView;
private PokemonReyclerViewAdapter pokemonReyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pokemon_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Context context = getContext();
        pokemonReyclerViewAdapter = new PokemonReyclerViewAdapter(new ArrayList<Pokemon>());

        recyclerView.setAdapter(pokemonReyclerViewAdapter);
        recyclerView.setHasFixedSize(false);


        ViewModelProviders.of(this)
                .get(AllPokemonViewModel.class)
                .getPokemonList(context)
                .observe(this, new Observer<List<Pokemon>>() {
                    @Override
                    public void onChanged(List<Pokemon> pokemons) {
                        if(pokemons != null) {
                            pokemonReyclerViewAdapter.addItems(pokemons);
                        }
                    }
                });



        }

    }
