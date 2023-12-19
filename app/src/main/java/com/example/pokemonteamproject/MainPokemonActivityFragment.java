package com.example.pokemonteamproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class MainPokemonActivityFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;

    private PokemonReyclerViewAdapter pokemonReyclerViewAdapter;

    private int columnCount = 1;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pokemon_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        pokemonReyclerViewAdapter = new PokemonReyclerViewAdapter(new ArrayList<Pokemon>());






        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        Context context = getContext();
        if(pokemonReyclerViewAdapter == null) {
            pokemonReyclerViewAdapter = new PokemonReyclerViewAdapter(new ArrayList<Pokemon>());
        }
        if(columnCount <= 1){
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(pokemonReyclerViewAdapter);
        recyclerView.setHasFixedSize(false);
        ViewModelProviders.of(this)
                .get(AllPokemonViewModel.class)
                .getPokemonList(context)
                .observe(this, new Observer<List<Pokemon>>() {
                    @Override
                    public void onChanged(List<Pokemon> pokemons) {
                        if(pokemons != null){
                            pokemonReyclerViewAdapter.addItems(pokemons);
                        }
                    }
                });


    }
    public void AddPokemon(ArrayList<Pokemon> pokemonList){
        new Thread(new Runnable() {
            @Override
            public void run() {
               AppDatabase.getInstance(getContext())
                        .pokemonDAO()
                        .InsertList(pokemonList);
            }
        }).start();
    }
}