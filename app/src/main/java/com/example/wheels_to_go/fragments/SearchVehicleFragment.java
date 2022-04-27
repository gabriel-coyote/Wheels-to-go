package com.example.wheels_to_go.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.wheels_to_go.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchVehicleFragment extends Fragment {

    // Database Parameters of Aruze mySQL server
    private final String DBurl ="jdbc:mysql://cs4354-project.mysql.database.azure.com:3306/companydb?useSSL=false&requireSSL=false";
    private final String DBuser ="daddy@cs4354-project";
    private final String DBpassword = "ranger1213ST";
    private Connection myDbConn = null;


    private TextView car_list;
    private EditText car_make, car_model, car_year, car_platenumber;
    private TextView findCarButton, addCarButton;


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
        car_list = viewer.findViewById(R.id.car_list);

        //buttons_
        findCarButton = viewer.findViewById(R.id.searchInventory_textview_id);
        addCarButton = viewer.findViewById(R.id.add_textview_id);

        findCarButton.setOnClickListener(view -> findCar());
        addCarButton.setOnClickListener(view -> addCar());



        return viewer;
    }







    /* ********************************************************************** */
    private void findCar(){



    }

    /* ********************************************************************** */
    private void addCar(){


        boolean found = false;
        boolean customer = false;
        boolean mechanic = false;

        String PlateNum = car_platenumber.getText().toString().trim();
        String Results_textview = "";

        String customer_y_n = "";
        String mechanic_y_n = "";

        // Run network connection on different Thread; not main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            // Create our MySQL database connection
            Class.forName("com.mysql.jdbc.Driver");
            myDbConn = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            //Creating a statement to execute Queries
            Statement statement1 = myDbConn.createStatement();
            Statement statement2 = myDbConn.createStatement();


            ResultSet carResults = statement1.executeQuery("select * from vehicle");



            // Iterate through each row in the table result
            while(carResults.next() ){
                // If the current employee credentials match then set valid to true
                if(PlateNum.equals(carResults.getString("Platenumber"))){


                    // Get info on the vehicle
                    ResultSet infoResults = statement2.executeQuery("select c.Firstname, c.Lastname, m.Id from customer c, mechanic m WHERE m.Fixes='"+PlateNum+"' AND c.Platenumber='"+PlateNum+"';\n");
                    while (infoResults.next()){



                    }


                    // Assigning location text str d=to displays
                    String location;
                    if(carResults.getString("StoreRegistrationID").equals("66772345")){
                        location = "2230 Frankie Blvd";
                    }else if (carResults.getString("StoreRegistrationID").equals("66779234")){
                        location = "502 University St";
                    }else{
                        location = "Un-Assigned";
                    }


                    Results_textview =
                            "Vehicle Info: \n"+
                            "\t\t\t Price: "+
                                    "\t\t\t\t\t "+carResults.getInt("Price")+"k"+
                            "\t\t\t Store Located At: "+
                                    "\t\t\t\t\t "+location+
                            "\t\t\t Customer: "+
                                    "\t\t\t\t\t "+customer_y_n+
                            "\t\t\t Serviced: "+
                                    "\t\t\t\t\t "+mechanic_y_n;

                    car_list.setText(Results_textview);
                    found = true;
                    break;
                }

            }


            // Close our statement
            statement1.close();
            statement2.close();

            //Close our connection boi
            myDbConn.close();

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
