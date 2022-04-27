package com.example.wheels_to_go.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wheels_to_go.R;


public class ServicedFragment extends Fragment {


    View viewer;

    public ServicedFragment() {
        // Required empty public constructor
    }

    public static ServicedFragment newInstance(String param1, String param2) {
        ServicedFragment fragment = new ServicedFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

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

}