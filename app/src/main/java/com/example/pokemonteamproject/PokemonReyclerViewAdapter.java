package com.example.pokemonteamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonteamproject.db.Pokemon;

import java.util.List;

public class PokemonReyclerViewAdapter extends RecyclerView.Adapter<PokemonReyclerViewAdapter.ViewHolder> {

    public final List<Pokemon> pokemons;
    private EditText search;
    public PokemonReyclerViewAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonReyclerViewAdapter.ViewHolder holder, int position) {
        final Pokemon pokemon = pokemons.get(position);
        if(pokemon != null){
            holder.txtPokemonName.setText(pokemon.getName());
            //TODO

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("pokemon_pk", pokemon.getCID());

                    CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
                    courseDetailFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, courseDetailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }


    public void addItems(List<Pokemon> newpokemons) {
        if(pokemons != newpokemons){
            this.pokemons.clear();
            this.pokemons.addAll(newpokemons);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public Pokemon pokemon;
        public TextView txtPokemonName;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            view = itemView;
            txtPokemonName = view.findViewById(R.id.riPokemonName);
        }
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
