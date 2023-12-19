package com.example.pokemonteamproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;
import com.example.pokemonteamproject.db.Team;

import java.util.ArrayList;
import java.util.List;


public class OldTeamFragment extends Fragment {

    private ArrayList<Team> teams;
    private View view;
    private ListView oldListView;
    private ArrayList<String> pokemonList;
    private ArrayAdapter<String> adapter;
    private LiveData<List<Team>> teamLiveData;

    public OldTeamFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_team, container, false);
        oldListView = view.findViewById(R.id.oldTeamListView);

        pokemonList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, pokemonList);

        oldListView.setAdapter(adapter);
        teamLiveData = AppDatabase.getInstance(getContext())
                .teamDAO()
                .getAll();

        teamLiveData.observe(getViewLifecycleOwner(), new Observer<List<Team>>() {

            @Override
            public void onChanged(List<Team> teamList) {

                pokemonList.clear();
                for (Team team : teamList) {
                    pokemonList.add(team.getPokemonTeamName());
                }
                adapter.notifyDataSetChanged();
            }
        });

        oldListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team selectedTeam = teamLiveData.getValue().get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("team_id", selectedTeam.getCID());

                oldTeamDetails detailsFragment = new oldTeamDetails();
                detailsFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(android.R.id.content, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }
}
