package com.example.wheels_to_go.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.wheels_to_go.MainActivity;
import com.example.wheels_to_go.R;

public class SearchVehicleFragment extends Fragment {

    private TextView button_add;
    private TextView button_search;
    private TextView car_make;
    private TextView car_model;
    private TextView car_year;
    private TextView car_platenumber;

    View viewer;

    /* ********************************************************************** */
    public SearchVehicleFragment(){
        // Required empty public constructor
    }

    /* ********************************************************************** */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /* ********************************************************************** */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        // IF the viewer doesn't exist then make one
        // Else keep the same viewer
        if (viewer != null) {
            if ((ViewGroup)viewer.getParent() != null)
                ((ViewGroup)viewer.getParent()).removeView(viewer);
            return viewer;
        }else {
            viewer = inflater.inflate(R.layout.fragment_search_vehicle, container, false);
        }
        car_make = viewer.findViewById(R.id.car_make);
        car_model = viewer.findViewById(R.id.car_model);
        car_year = viewer.findViewById(R.id.car_year);
        car_platenumber = viewer.findViewById(R.id.car_platenumber);


        button_add = viewer.findViewById(R.id.add_textview_id);
        button_search = viewer.findViewById(R.id.searchInventory_textview_id);


        button_add.setOnClickListener(view -> store_vehicle());


        return viewer;
    }


    private void store_vehicle(){

    }







    /* ********************************************************************** */
    /* FUNCTION NAME:    alertDialog
       INPUT:            A String
       OUTPUT:           n/a
       PURPOSE:          To make the code more readable,
                         outputs an alert style text box    */
    private void alertDialog(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


}
