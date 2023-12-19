package com.example.pokemonteamproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;
import com.example.pokemonteamproject.db.Team;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateTeamFragment extends Fragment {
    private View view;
    private Button addBtn;
    private GetPokemon task;
    private Button saveBtn;

    private ArrayAdapter<String> adapter;
    Pokemon pokemon;
    private int teamSizeCounter;
    ListView listView;
    ArrayList<String> savedPokemonNames;
    private PokemonViewModel viewModel;
    private TextView testtText;
    PokemonReyclerViewAdapter pokemonReyclerViewAdapter;
    private TextInputEditText txtTeamName;
    private int tempPokePK;
    private int receivedPokemonPk = -1;

    public void setReceivedPokemonPk(int pokemonPk) {
        receivedPokemonPk = pokemonPk;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        // Inflate the layout for this

        view = inflater.inflate(R.layout.fragment_create_team, container, false);
        addBtn = view.findViewById(R.id.btnAdd);
        testtText = view.findViewById(R.id.testTxtt);
        listView = view.findViewById(R.id.listView);

        if (savedPokemonNames == null) {
            savedPokemonNames = viewModel.getSavedPokemonNames();
        }

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, savedPokemonNames);
        listView.setAdapter(adapter);

        Bundle thisbundle = this.getArguments();
        if(thisbundle != null) {
            tempPokePK = thisbundle.getInt("create_pokemon_pk");
            teamSizeCounter++;
            viewModel.setPokemonCounter(teamSizeCounter);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    pokemon = AppDatabase.getInstance(getContext())
                            .pokemonDAO()
                            .getByID(tempPokePK);

                    final String pokemonName = pokemon.getName();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (viewModel.canAddPokemonToTeam()) {
                                String capitalizedPokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
                                viewModel.addPokemonName(capitalizedPokemonName);
                                viewModel.addPokemonNumber(Integer.toString(tempPokePK));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }).start();
        }


            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task = new GetPokemon();
                    task.OnPokemonListImportListener(new GetPokemon.OnPokemonListImport() {
                        @Override
                        public void completedPokemonList(ArrayList<Pokemon> pokemons) {
                            final ArrayList<Pokemon> pokemonList = new ArrayList<>();

                            for (Pokemon p : pokemons) {
                                Log.d("PokemonRecycler", "completedPokemonList: ");

                                Pokemon convertPokemon = new Pokemon(-1, "test", p.getName());
                                pokemonList.add(convertPokemon);
                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if (pokemonReyclerViewAdapter == null) {
                                        pokemonReyclerViewAdapter = new PokemonReyclerViewAdapter(pokemonList);
                                    }
                                    pokemonReyclerViewAdapter.addItems(pokemonList);
                                    MainPokemonActivityFragment mainPokemonActivityFragment = new MainPokemonActivityFragment();
                                    mainPokemonActivityFragment.AddPokemon(pokemonList);
                                }
                            }).start();
                        }
                    });
                    task.execute("");

                    if (view.getId() == R.id.btnAdd) {
                        switchToPokemonRyclerFragment();
                    }
                }
            });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txtTeamName = view.findViewById(R.id.textTeamName);
        saveBtn = view.findViewById(R.id.btnSave);

        int PokemonCountertemp = viewModel.getPokemonCounter();
        saveBtn.setEnabled(!txtTeamName.getText().toString().trim().isEmpty() && PokemonCountertemp == 6);

        txtTeamName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int PokemonCountertemp = viewModel.getPokemonCounter();
                saveBtn.setEnabled(!charSequence.toString().trim().isEmpty() && PokemonCountertemp == 6);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String dTeamName = txtTeamName.getText().toString();
                final ArrayList<String> tempPokemonNames = viewModel.getSavedPokemonNumber();
                final ArrayList<String> tempPokemonNumbers = viewModel.getSavedPokemonNames();




                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getContext())
                                .teamDAO()
                                .insert(new Team(dTeamName, tempPokemonNames, tempPokemonNumbers));

                        LiveData<List<Team>> teams = AppDatabase.getInstance((getContext()))
                                .teamDAO()
                                .getAll();
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.clear();
                                viewModel.clearData();
                                adapter.notifyDataSetChanged();
                                viewModel.resetPokemonCounter();

                            }
                        });
                        /*
                        AppDatabase.getInstance(getContext())
                                .pokemonDAO()
                                .insert(new Pokemon(dId, dTeamName, dPokemonName));

                        List<Pokemon> pokemons =AppDatabase.getInstance(getContext())
                                .pokemonDAO()
                                .getAll();

                        Log.d("Course", "_______________Start___________________");
                        for(Pokemon c:pokemons){
                            Log.d("Pokemon", c.toString());
                        }

                        Log.d("Course", "________________End__________________");

                    */

                    }
                }).start();


              //

                //TODO zero out the listView
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_layout, new OldTeamFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void resetListView(){
        viewModel.clearData();
        adapter.clear();
        adapter.notifyDataSetChanged();
    }


    private void switchToPokemonRyclerFragment() {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_layout, new PokemonRecycler())
                .addToBackStack(null)
                .commit();
    }

}