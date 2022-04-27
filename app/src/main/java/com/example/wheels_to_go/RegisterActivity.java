package com.example.wheels_to_go;

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

public class RegisterActivity extends AppCompatActivity {

    // Database Parameters of Aruze mySQL server
    private final String DBurl ="jdbc:mysql://cs4354-project.mysql.database.azure.com:3306/companydb?useSSL=false&requireSSL=false";
    private final String DBuser ="daddy@cs4354-project";
    private final String DBpassword = "ranger1213ST";
    private Connection myDbConn = null;

    private EditText managerID;

    private EditText fname, lname, address, sex, birthDate;
    private EditText salary, ssn, worksAt, empID;
    private TextView button_register;

    /* ********************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        // Binding to layout textfields
        fname = findViewById(R.id.fist_name);
        lname = findViewById(R.id.last_name);
        worksAt = findViewById(R.id.location);
        address  = findViewById(R.id.address);
        salary = findViewById(R.id.salary);
        sex = findViewById(R.id.sex);
        empID = findViewById(R.id.eployee_ID);
        birthDate = findViewById(R.id.birth_date);
        ssn = findViewById(R.id.SSN);

        managerID = findViewById(R.id.manager_id_input);


        button_register = findViewById(R.id.button_save);
        button_register.setOnClickListener(view -> { registerEmployee();});
    }



    /* ********************************************************************** */
    private void registerEmployee(){

        String firstName = fname.getText().toString().trim();
        String lastName = lname.getText().toString().trim();
        String Address = address.getText().toString().trim();
        String Sex = sex.getText().toString().trim();
        String Birthdate = birthDate.getText().toString().trim();
        String Salary = salary.getText().toString().trim();
        String SSN = ssn.getText().toString().trim();
        String WorksAt = worksAt.getText().toString().trim();
        String EmpID = empID.getText().toString().trim();

        String ManagerID =  managerID.getText().toString().trim();

        // We only have two valid managers; one per store
        if(ManagerID.equals("3356") || ManagerID.equals("7891")){
            // Valid Manager; so create/insert employee in database
            //alertDialog("valid MANAGER");


            // Run network connection on different Thread; not main thread
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try{
                // Create our MySQL database connection
                Class.forName("com.mysql.jdbc.Driver");
                myDbConn = DriverManager.getConnection(DBurl, DBuser, DBpassword);

                String query = "INSERT INTO EMPLOYEE VALUES ("+Salary+", '"+Sex+"', '"+Address+"', '"+Birthdate+"', '"+lastName+"', '"+firstName+"', "+SSN+", "+WorksAt+", "+EmpID+")";
                //Creating a statement to execute Queries
                Statement statement = myDbConn.createStatement();
                statement.executeUpdate(query);


                alertDialog("Created Employee " +EmpID+": "+ firstName);


                // Close our statement
                statement.close();
                // Close our connection
                myDbConn.close();

                // Go back to login page/activity
                this.onBackPressed();


            } catch (Exception e){
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }






        }else{
            alertDialog("Invalid Manager ID Credentials!");

        }
    }


    /* ********************************************************************** */
    /* FUNCTION NAME:    alertDialog
       INPUT:            A String
       OUTPUT:           n/a
       PURPOSE:          To make the code more readable,
                         outputs an alert style text box    */
    private void alertDialog(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
