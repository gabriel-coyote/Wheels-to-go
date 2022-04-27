package com.example.wheels_to_go;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {


    // Database Parameters of Aruze mySQL server
    private final String DBurl ="jdbc:mysql://cs4354-project.mysql.database.azure.com:3306/companydb?useSSL=false&requireSSL=false";
    private final String DBuser ="daddy@cs4354-project";
    private final String DBpassword = "ranger1213ST";
    private Connection myDbConn = null;


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
        login_btn.setOnClickListener(view -> logUserIn());


        enteredFname = findViewById(R.id.editTextTextPersonName);
        enteredPassword = findViewById(R.id.editTextPassword);

    }


    /* ********************************************************************** */
    private void logUserIn(){

        boolean valid = false;
        boolean found = false;

        String fname = enteredFname.getText().toString().trim();
        String password = enteredPassword.getText().toString().trim();
        int password_id = Integer.parseInt(password);


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
            Statement statement3 = myDbConn.createStatement();
            Statement statement4 = myDbConn.createStatement();


            // Check if attempted login is for a CLERK, MANAGER, MECHANIC or REGULAR employee
            ResultSet resultsCLERK = statement1.executeQuery("select distinct emp.Firstname, emp.Id from employee emp, clerk c where c.Id=emp.Id");
            ResultSet resultsMANAGER = statement2.executeQuery("select distinct emp.Firstname, emp.Id from employee emp, manager m where m.Id=emp.Id");
            ResultSet resultsMECHANIC = statement3.executeQuery("select distinct emp.Firstname, emp.Id from employee emp, mechanic m where m.Id=emp.Id");
            ResultSet resultsREGULAR = statement4.executeQuery("select * from employee");

            // Iterate through each row in the clerks table result
            while(resultsCLERK.next() && !found){
                // If the current employee credentials match then set valid to true
                if(fname.equals(resultsCLERK.getString("Firstname")) &&
                    password_id==resultsCLERK.getInt("Id")){

                    MainActivity.LoggedInUser = fname;
                    MainActivity.LoggedInUserType = "Clerk";
                    valid = true;
                    found = true;
                    break;
                }

            }


            // Iterate through each row in the manager table result
            while(resultsMANAGER.next() && !found){
                // If the current employee credentials match then set valid to true
                if(fname.equals(resultsMANAGER.getString("Firstname")) &&
                        password_id==resultsMANAGER.getInt("Id")){

                    MainActivity.LoggedInUser = fname;
                    MainActivity.LoggedInUserType = "Manager";
                    valid = true;
                    found = true;
                    break;
                }

            }

            // Iterate through each row in the mechanics table result
            while(resultsMECHANIC.next() && !found){
                // If the current employee credentials match then set valid to true
                if(fname.equals(resultsMECHANIC.getString("Firstname")) &&
                        password_id==resultsMECHANIC.getInt("Id")){

                    MainActivity.LoggedInUser = fname;
                    MainActivity.LoggedInUserType = "Mechanic";
                    valid = true;
                    found = true;
                    break;
                }

            }

            // Iterate through each row in the employee table result
            while(resultsREGULAR.next() && !found){
                // If the current employee credentials match then set valid to true
                if(fname.equals(resultsREGULAR.getString("Firstname")) &&
                        password_id==resultsREGULAR.getInt("Id")){

                    MainActivity.LoggedInUser = fname;
                    MainActivity.LoggedInUserType = "Employee";
                    valid = true;
                    found = true;
                    break;
                }

            }
            // Close our statement
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            //Close our connection boi
            myDbConn.close();

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
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
