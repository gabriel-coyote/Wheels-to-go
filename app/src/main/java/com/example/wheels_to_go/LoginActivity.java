package com.example.wheels_to_go;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {


    private EditText enteredPassword, enteredFname;
    private TextView register_btn, login_btn;

    /* ********************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_btn = findViewById(R.id.textView_register);
        register_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
                });


        login_btn = findViewById(R.id.textView_login);
        login_btn.setOnClickListener(view -> { logUserIn();});


        enteredFname = findViewById(R.id.editTextTextPersonName);
        enteredPassword = findViewById(R.id.editTextPassword);

    }


    /* ********************************************************************** */
    private void logUserIn(){

        Boolean valid = false;

        String fname = enteredFname.getText().toString().trim();
        String password = enteredPassword.getText().toString().trim();
        int password_id = Integer.parseInt(password);



        try{
            alertDialog("Attempting conection...");
            // Create our MySQL database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect_BOI = DriverManager.getConnection("jdbc:mysql://localhost:3306/CompanyDB","root","ranger1213st");

            //Creating a statement to execute Queries
            Statement statement = connect_BOI.createStatement();

            String query = "select Firstname, Id from employee where Firstname='John' and Id=3356";

            //Execute the query, and get a hav result set
            //ResultSet results = statement.executeQuery("SELECT * FROM EMPLOYEE");
            ResultSet results = statement.executeQuery(query);

            if(results.next()){
                valid = true;
            }
            // Iterate through the results to find if employee exists
            /*
            while(results.next()){

                alertDialog("Parsing through results");
                String firstName = results.getString("Firstname");
                int ID = results.getInt("Id");

                System.out.println(results.getString("Firstname") + ", " + results.getInt("Id"));
                // If valid login matches an employee ;log in
                if(fname.equals(firstName) && password_id==ID){
                    valid = true;
                    break;

                }

                valid = true;

            }


             */
            // Close our statement
            statement.close();

            //Close our connection boi
            connect_BOI.close();

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }


        if(valid){
            // Switch to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            alertDialog("Invalid Credentials 😐");
        }
    }



    /* ********************************************************************** */

    private void alertDialog(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
