package com.example.pokemonteamproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Pokemon;

import java.io.IOException;
import java.net.URL;


public class CourseDetailFragment extends Fragment {

    View view;
    TextView textTest;
    Pokemon pokemon;
    ImageView imageSprite;
    String capName;

    private AppCompatButton detailsavebtn;

    int pokemon_pk;
    URL imageSpriteURL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_course_detail, container, false);
        textTest = view.findViewById(R.id.txtTest);
        imageSprite = view.findViewById(R.id.SpriteImage);




        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pokemon_pk = bundle.getInt("pokemon_pk");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    pokemon = AppDatabase.getInstance(getContext())
                            .pokemonDAO()
                            .getByID(pokemon_pk);
                    capName = pokemon.getName();



                    capName = capName.substring(0, 1).toUpperCase() + capName.substring(1);
                    textTest.setText(capName);
                }
            }).start();
        }


        // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png

        Bitmap bmp = null;
        try {
            imageSpriteURL =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ pokemon_pk+".png" );
            bmp = BitmapFactory.decodeStream(imageSpriteURL.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageSprite.setImageBitmap(bmp);

        //Save Button
        detailsavebtn = view.findViewById(R.id.saveDetailsButton);

        detailsavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requireActivity().getSupportFragmentManager().popBackStack();

                Bundle pkBundle = new Bundle();
                pkBundle.putInt("create_pokemon_pk", pokemon_pk);

                CreateTeamFragment createTeamFragment = new CreateTeamFragment();
                createTeamFragment.setArguments(pkBundle);


                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_layout, createTeamFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });





        return view;
    }


}