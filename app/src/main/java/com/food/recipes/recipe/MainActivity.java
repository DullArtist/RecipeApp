package com.food.recipes.recipe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.food.recipes.recipe.databinding.ActivityMainBinding;
import com.food.recipes.recipe.fragments.Explore;
import com.food.recipes.recipe.fragments.Home;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.steel_while));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,new Home());
        transaction.commit();

        binding.navBar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Home()).commit();
                    break;
                case R.id.explore:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Explore()).commit();
                    break;
            }

            return true;
        });

    }

}