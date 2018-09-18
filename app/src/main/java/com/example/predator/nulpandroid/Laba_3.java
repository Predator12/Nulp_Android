package com.example.predator.nulpandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Laba_3 extends AppCompatActivity {

    EditText firstName,lastName,email,phone,password,cnfpass;
    Button submit;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laba_3);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        updateUI();

    }
    public void updateUI(){
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        cnfpass = findViewById(R.id.cnfpass);
        submit = findViewById(R.id.submit);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(Laba_3.this, R.id.firstName, "[a-zA-Z\\s]+",R.string.firstNameerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.lastName,  "[a-zA-Z\\s]+",R.string.lastNameerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.email, android.util.Patterns.EMAIL_ADDRESS,R.string.emailerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.phone, RegexTemplate.TELEPHONE,R.string.phoneerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.password,  regexPassword,R.string.passworderr);
        awesomeValidation.addValidation(Laba_3.this, R.id.cnfpass, R.id.password,R.string.cnfpasserr);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(Laba_3.this, "Data Received Succesfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Laba_3.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });{


        }

    }

}
