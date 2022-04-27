package com.example.wheels_to_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheels_to_go.fragments.EditProfileTabFragment;
import com.example.wheels_to_go.fragments.SearchVehicleFragment;
import com.example.wheels_to_go.fragments.ServicedFragment;
import com.example.wheels_to_go.fragments.findEmployeeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // Hold Our Current Logged In User
    public static String LoggedInUser;
    public static String LoggedInUserType;
    private TextView welcomeUserText;

    // Declaring our fragments to use with loadfragments()
    final Fragment fragment_searchVehicle = new SearchVehicleFragment();
    final Fragment fragment_profile = new EditProfileTabFragment();
    final Fragment fragment_findEmployee = new findEmployeeFragment();
    final Fragment fragment_serviced = new ServicedFragment();




    public static NavigationView navigationView;
    /* ********************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigationView = findViewById(R.id.menu_navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.menu_profile:
                    loadFragment(fragment_profile);
                    break;
                case R.id.menu_inventory:
                    loadFragment(fragment_searchVehicle);
                    break;
                case R.id.menu_employee:
                    loadFragment(fragment_findEmployee);
                    break;

                case R.id.menu_serviced:
                    loadFragment(fragment_serviced);
                    break;
                case R.id.menu_logOut:
                    //TODO: Log employee out
                    logout();
                    break;
                default: break;
            }

            return true;
        });

        // Set our default fragment once employee has logged in
        navigationView.setCheckedItem(R.id.menu_profile);


        welcomeUserText = findViewById(R.id.User);
        welcomeUserText.setText("Welcome, \n"+LoggedInUserType+" "+LoggedInUser);
    }




    /* ********************************************************************** */
    private void logout(){

        // Switch to login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /* ********************************************************************** */
    /* FUNCTION NAME:    loadFragment
       INPUT:            A Fragment
       OUTPUT:           n/a
       PURPOSE:          Switches/loads a fragment into the main fragment container */
    public void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}