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


public class findEmployeeFragment extends Fragment {

    // Database Parameters of Aruze mySQL server
    private final String DBurl ="jdbc:mysql://cs4354-project.mysql.database.azure.com:3306/companydb?useSSL=false&requireSSL=false";
    private final String DBuser ="daddy@cs4354-project";
    private final String DBpassword = "ranger1213ST";
    private Connection myDbConn = null;

    private EditText empName, empID;
    private TextView searchEmpButton, searchResults;

    View viewer;

    /* ********************************************************************** */
    public findEmployeeFragment() {
        // Required empty public constructor
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
            if (viewer.getParent() != null)
                ((ViewGroup)viewer.getParent()).removeView(viewer);
            return viewer;
        }else {
            viewer = inflater.inflate(R.layout.fragment_search_emp, container, false);
        }


        empID = viewer.findViewById(R.id.input_emp_id);
        empName = viewer.findViewById(R.id.input_emp_name);
        searchResults = viewer.findViewById(R.id.find_emp_result);

        searchEmpButton = viewer.findViewById(R.id.searchEMP_textview_id);
        searchEmpButton.setOnClickListener(view -> search());

        return viewer;
    }


    /* ********************************************************************** */
    private void search(){

        boolean found = false;

        String EmpID = empID.getText().toString().trim();
        String EmpName = empName.getText().toString().trim();
        int EmpID_int = Integer.parseInt(EmpID);
        String resultsEmp = "";

        // Run network connection on different Thread; not main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            // Create our MySQL database connection
            Class.forName("com.mysql.jdbc.Driver");
            myDbConn = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            //Creating a statement to execute Queries
            Statement statement1 = myDbConn.createStatement();

            // Check if attempted login is in employees
            ResultSet results = statement1.executeQuery("select * from employee");


            // Iterate through each row in the employee table result
            while(results.next() ){
                // If the current employee credentials match then set valid to true
                if(EmpName.equals(results.getString("Firstname")) &&
                        EmpID_int==results.getInt("Id")){

                    resultsEmp = "First Name:     \n\t\t\t\t" + results.getString("Firstname") + '\n' +
                                 "Last Name:      \n\t\t\t\t" + results.getString("Lastname") + '\n' +
                                 "Employee ID:    \n\t\t\t\t" + results.getInt("Id") + '\n' +
                                 "Store Assigned: \n\t\t\t\t" + results.getInt("WorksAt") + '\n' +
                                 "Salary:         \n\t\t\t\t" + results.getInt("Salary") + '\n' +
                                 "Sex:            \n\t\t\t\t" + results.getString("Sex") + '\n' +
                                 "Birth Date:     \n\t\t\t\t" + results.getDate("Birthdate");


                    searchResults.setText(resultsEmp);


                    found = true;
                    break;
                }

            }
            // Close our statement
            statement1.close();

            //Close our connection boi
            myDbConn.close();

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


        if(!found){
            alertDialog("Invalid Credentials 😐");
        }
    }


    /* ********************************************************************** */

    private void alertDialog(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}