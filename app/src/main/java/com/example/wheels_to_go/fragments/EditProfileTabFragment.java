package com.example.wheels_to_go.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wheels_to_go.R;


public class EditProfileTabFragment extends Fragment {

    View viewer;

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

        return viewer;
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