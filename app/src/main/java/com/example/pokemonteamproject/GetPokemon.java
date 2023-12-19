package com.example.pokemonteamproject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pokemonteamproject.db.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GetPokemon extends AsyncTask<String, Integer, String> {
PokemonResponse pokemonResponse;
    private String rawJSON;
    ArrayList<Pokemon> pokemonList = new ArrayList<>();
    ArrayList<Pokemon> pokemonList2 = new ArrayList<>();
    private OnPokemonListImport listener;

    public interface OnPokemonListImport{
        void completedPokemonList(ArrayList<Pokemon> pokemons);
    }

    public void OnPokemonListImportListener(OnPokemonListImport listenerfromMain){
        listener = listenerfromMain;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/?limit=151");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            if (status == 200) {
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();


                Gson gson = new Gson();
                PokemonResponse response = gson.fromJson(stringBuilder.toString(), PokemonResponse.class);
                if (response != null && response.getResults() != null) {
                    pokemonList = response.getResults();
                }

                Log.d("getPokemon", "doInBackground: " + stringBuilder.toString());
            }
        } catch (Exception e) {
            Log.e("getPokemon", "doInBackground: " + e.toString());
        }

//        try {
//            URL url2 = new URL("https://pokeapi.co/api/v2/pokemon/10/");
//
//            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
//            connection.setRequestMethod("GET");
//
//            int status = connection.getResponseCode();
//
//            if (status == 200) {
//                BufferedReader bufferedReader =
//                        new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                bufferedReader.close();
//
//                // Parse the JSON response using Gson
//                Gson gson = new Gson();
//
//
//                Log.d("getPokemon", "doInBackground: " + stringBuilder.toString());
//            }
//        } catch (Exception e) {
//            Log.e("getPokemon", "doInBackground: " + e.toString());
//        }




        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Pokemon[] pokemons;
        try{
            //pokemons = parseJson();
            listener.completedPokemonList(pokemonList);


        }catch (Exception e){
            Log.d("GetPokemon","onPostExecute"+ e.toString());
        }
        super.onPostExecute(s);
    }

    private Pokemon[] parseJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Pokemon[] pokemons = null;



        try{
            pokemons = gson.fromJson(rawJSON, Pokemon[].class);
        }catch (Exception e){
            Log.d("GetPokemon","onPostExecute"+ e.toString());
        }
        return pokemons;
    }
}
