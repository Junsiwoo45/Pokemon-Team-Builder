package com.example.pokemonteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    BottomNavigationView btnNav;
    String path = "https://blog.logomyway.com/wp-content/uploads/2021/05/pokemon-logo-png.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNav = findViewById(R.id.bottomNav);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btnNav.setOnNavigationItemSelectedListener(navListener);




            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, new FrontPageFragment())
                    .commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;


            if (item.getItemId() == R.id.item1) {
                selectedFragment = new FrontPageFragment();

            }
            if (item.getItemId() == R.id.item2) {
                selectedFragment = new CreateTeamFragment();
            }
            if (item.getItemId() == R.id.item3) {
                selectedFragment = new OldTeamFragment();
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }

    };
}