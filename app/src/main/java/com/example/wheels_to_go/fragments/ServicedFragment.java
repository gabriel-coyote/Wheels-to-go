package com.example.wheels_to_go.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wheels_to_go.R;

import java.sql.Connection;


public class ServicedFragment extends Fragment {

    // Database Parameters of Aruze mySQL server
    private final String DBurl ="jdbc:mysql://cs4354-project.mysql.database.azure.com:3306/companydb?useSSL=false&requireSSL=false";
    private final String DBuser ="daddy@cs4354-project";
    private final String DBpassword = "ranger1213ST";
    private Connection myDbConn = null;

    View viewer;



    /* ********************************************************************** */
    public ServicedFragment() {
        // Required empty public constructor
    }


    /* ********************************************************************** */
    public static ServicedFragment newInstance(String param1, String param2) {
        ServicedFragment fragment = new ServicedFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    /* ********************************************************************** */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }



    /* ********************************************************************** */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // IF the viewer doesn't exist then make one
        // Else keep the same viewer
        if (viewer != null) {
            if ((ViewGroup) viewer.getParent() != null)
                ((ViewGroup) viewer.getParent()).removeView(viewer);
            return viewer;
        } else {
            viewer = inflater.inflate(R.layout.serviced_cars, container, false);


            return viewer;
        }
    }



    /* ********************************************************************** */
    private void find(){



        boolean found = false;

        // Run network connection on different Thread; not main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try{

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }




        if(!found){
            alertDialog("Invalid Info 😐");
        }
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