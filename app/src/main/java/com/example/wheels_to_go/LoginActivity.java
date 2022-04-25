package com.example.wheels_to_go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.Manifest;

public class LoginActivity extends AppCompatActivity {


    private static String ip = "172.69.70.124";
    private static String port = "3306";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "CompanyDB";
    private static String username = "root";
    private static String password = "ranger1213st";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

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
        login_btn.setOnClickListener(view -> { logUserIn2();});



        enteredFname = findViewById(R.id.editTextTextPersonName);
        enteredPassword = findViewById(R.id.editTextPassword);

    }


    public void start(View view){
        ActivityCompat.requestPermissions(this, new String[]{}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Class.forName(Classes);

            connection = DriverManager.getConnection(url, username, password);
            alertDialog("Connected");
        }catch (Exception e){

        }
    }


    /* ********************************************************************** */
    private void logUserIn2(){
        boolean valid = false;

        String employee_name = "Gabe";
        int emp_password = 12345;

        String input_name = enteredFname.getText().toString().trim();
        String input_password = enteredPassword.getText().toString().trim();
        int input_password_ = Integer.parseInt(input_password);

        if(input_name.equals(employee_name) && input_password_==emp_password){
            valid = true;
        }else{
            //alertDialog("Incorrect");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }



    }

    /* ********************************************************************** */
    /* ********************************************************************** */
    private void logUserIn(){

        Boolean valid = false;

        String fname = enteredFname.getText().toString().trim();
        String password = enteredPassword.getText().toString().trim();
        int password_id = Integer.parseInt(password);



        try{
            alertDialog("Attempting conection...");
            // Create our MySQL database connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = null;
            DriverManager.getConnection("jdbc:mysql://localhost:3306/CompanyDB?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");

            //Connection connect_BOI = DriverManager.getConnection("jdbc:mysql://localhost:3306/CompanyDB?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC","root","ranger1213st");

            //Creating a statement to execute Queries
           // Statement statement = connect_BOI.createStatement();
            Statement statement = conn.createStatement();


            String query = "select Firstname, Id from employee where Firstname='John' and Id=3356";

            //Execute the query, and get a hav result set
            //ResultSet results = statement.executeQuery("SELECT * FROM EMPLOYEE");
            ResultSet results = statement.executeQuery(query);


            //alertDialog(results.getString("Firstname"));

            if(results.getString("Firstname").isEmpty()){
                alertDialog("Empyt Result: firstname");
            }else{
                alertDialog("Result: firstname not empty");
            }
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
            //connect_BOI.close();

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


        valid = true;
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
