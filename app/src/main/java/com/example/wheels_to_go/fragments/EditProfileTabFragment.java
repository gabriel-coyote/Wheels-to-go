package com.example.wheels_to_go.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.wheels_to_go.R;


public class EditProfileTabFragment extends Fragment {

    View viewer;
    private TextView first_name;
    private TextView last_name;
    private TextView location;
    private TextView address;
    private TextView sex;
    private TextView birth_date;
    private TextView employee_ID;
    private TextView SSN;
    private TextView button_save;


    /* ********************************************************************** */
    public EditProfileTabFragment(){
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
            viewer = inflater.inflate(R.layout.fragment_edit_profile_edit_tab, container, false);
        }
        first_name = viewer.findViewById(R.id.fist_name);
        last_name = viewer.findViewById(R.id.last_name);
        location = viewer.findViewById(R.id.location);
        address = viewer.findViewById(R.id.address);
        sex = viewer.findViewById(R.id.sex);
        birth_date = viewer.findViewById(R.id.birth_date);
        employee_ID = viewer.findViewById(R.id.eployee_ID);
        SSN = viewer.findViewById(R.id.SSN);

        button_save = viewer.findViewById(R.id.button_save);
        button_save.setOnClickListener(view -> changeInfo());



        return viewer;
    }


    /* ********************************************************************** */
    private void changeInfo(){
        String fname_input = first_name.getText().toString().trim();
        String lname_input = last_name.getText().toString().trim();
        String location_input = location.getText().toString().trim();
        String address_input = address.getText().toString().trim();
        String sex_input = sex.getText().toString().trim();
        String birth_date_input = birth_date.getText().toString().trim();
        String employee_ID_input = employee_ID.getText().toString().trim();
        String SSN_input = SSN.getText().toString().trim();

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