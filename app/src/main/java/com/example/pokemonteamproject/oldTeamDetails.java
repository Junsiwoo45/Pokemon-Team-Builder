package com.example.pokemonteamproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemonteamproject.db.AppDatabase;
import com.example.pokemonteamproject.db.Team;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class oldTeamDetails extends Fragment {

    private ArrayList<String> teamList;
    private ArrayList<String> teamNumberList;
    int team_pk;
    TextView txtPokeOne, txtPokeTwo, txtPokeThree, txtPokeFour, txtPokeFive, txtPokeSix;
    ImageView imageViewOne, imageViewTwo, imageViewThree, imageViewFour, imageViewFive, imageViewSix;

    String CapName1, CapName2, CapName3, CapName4, CapName5, CapName6;
    Team team;
    String temp1, temp2, temp3, temp4, temp5, temp6;

    Bitmap bmp1 = null, bmp2 = null, bmp3 = null, bmp4 = null, bmp5 = null,bmp6 = null;
    URL urlSprite1;

    AppCompatButton goBackBtn, deleteBtn;
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_team_details, container, false);

        txtPokeOne = view.findViewById(R.id.txtPokemonOne);
        txtPokeTwo = view.findViewById(R.id.txtPokemonTwo);
        txtPokeThree = view.findViewById(R.id.txtPokemonThree);
        txtPokeFour = view.findViewById(R.id.txtPokemonFour);
        txtPokeFive = view.findViewById(R.id.txtPokemonFive);
        txtPokeSix = view.findViewById(R.id.txtPokemonSix);

        imageViewOne = view.findViewById(R.id.detailimageView1);
        imageViewTwo = view.findViewById(R.id.detailimageView2);
        imageViewThree = view.findViewById(R.id.detailimageView3);
        imageViewFour = view.findViewById(R.id.detailimageView4);
        imageViewFive = view.findViewById(R.id.detailimageView5);
        imageViewSix = view.findViewById(R.id.detailimageView6);

       goBackBtn = view.findViewById(R.id.goBackButton);
       deleteBtn = view.findViewById(R.id.deleteButton);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });



        Bundle bundle = this.getArguments();
        if(bundle != null){
            team_pk = bundle.getInt("team_id");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    team = AppDatabase.getInstance(getContext())
                            .teamDAO()
                            .getByID(team_pk);
                    teamList = team.getListPokemon();
                    teamNumberList = team.getPokemonNumber();
                    CapName1 = teamList.get(0);
                    CapName2 = teamList.get(1);
                    CapName3 = teamList.get(2);
                    CapName4 = teamList.get(3);
                    CapName5 = teamList.get(4);
                    CapName6 = teamList.get(5);

                    CapName1 = CapName1.substring(0, 1).toUpperCase() + CapName1.substring(1);
                    CapName2 = CapName2.substring(0, 1).toUpperCase() + CapName2.substring(1);
                    CapName3 = CapName3.substring(0, 1).toUpperCase() + CapName3.substring(1);
                    CapName4 = CapName4.substring(0, 1).toUpperCase() + CapName4.substring(1);
                    CapName5 = CapName5.substring(0, 1).toUpperCase() + CapName5.substring(1);
                    CapName6 = CapName6.substring(0, 1).toUpperCase() + CapName6.substring(1);



                    txtPokeOne.setText(CapName1);
                    txtPokeTwo.setText(CapName2);
                    txtPokeThree.setText(CapName3);
                    txtPokeFour.setText(CapName4);
                    txtPokeFive.setText(CapName5);
                    txtPokeSix.setText(CapName6);


                     temp1 = teamNumberList.get(0);
                     temp2 = teamNumberList.get(1);
                     temp3 = teamNumberList.get(2);
                     temp4 = teamNumberList.get(3);
                     temp5 = teamNumberList.get(4);
                     temp6 = teamNumberList.get(5);
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp1 +".png" );
                        bmp1 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp2 +".png" );
                        bmp2 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp3 +".png" );
                        bmp3 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp4 +".png" );
                        bmp4 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp5 +".png" );
                        bmp5 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {

                        urlSprite1 =  new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ temp6 +".png" );
                        bmp6 = BitmapFactory.decodeStream(urlSprite1.openConnection().getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageViewOne.setImageBitmap(bmp1);
                            imageViewTwo.setImageBitmap(bmp2);
                            imageViewThree.setImageBitmap(bmp3);
                            imageViewFour.setImageBitmap(bmp4);
                            imageViewFive.setImageBitmap(bmp5);
                            imageViewSix.setImageBitmap(bmp6);

                        }



                });
            }
            }).start();


        }

        if(deleteBtn != null) {
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getInstance(getContext())
                                    .teamDAO()
                                    .delete(team);
                        }
                    }).start();
                    requireActivity().onBackPressed();
                }
            });
        }



        return view;
    }
}