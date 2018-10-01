package com.example.predator.nulpandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Laba_3 extends AppCompatActivity {

    EditText firstName, lastName, email, phone, password, cnfpass;
    Button submit, usersList;
    AwesomeValidation awesomeValidation;

    String firstNameStr;
    String lastNameStr;
    String emailStr;
    String phoneStr;
    String passwordStr;
    String confirmPasswordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laba_3);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        updateUI();
    }

    public void updateUI() {
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        cnfpass = findViewById(R.id.cnfpass);
        submit = findViewById(R.id.submit);
        usersList = findViewById(R.id.users_list);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(Laba_3.this, R.id.firstName, "[a-zA-Z\\s]+", R.string.firstNameerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.lastName, "[a-zA-Z\\s]+", R.string.lastNameerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.phone, RegexTemplate.TELEPHONE, R.string.phoneerr);
        awesomeValidation.addValidation(Laba_3.this, R.id.password, regexPassword, R.string.passworderr);
        awesomeValidation.addValidation(Laba_3.this, R.id.cnfpass, R.id.password, R.string.cnfpasserr);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameStr = firstName.getText().toString();
                lastNameStr = lastName.getText().toString();
                emailStr = email.getText().toString();
                phoneStr = phone.getText().toString();
                passwordStr = password.getText().toString();
                confirmPasswordStr = cnfpass.getText().toString();
                if (awesomeValidation.validate()) {
                    Toast.makeText(Laba_3.this, "Data Received Succesfully", Toast.LENGTH_SHORT).show();
                    saveAndCleanUserData();
                } else {
                    Toast.makeText(Laba_3.this, "Error", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(Laba_3.this, awesomeValidation.validate() ? "Data Received Succesfully" : "Error", Toast.LENGTH_SHORT).show();
            }
        });

        usersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab4Activity = new Intent(Laba_3.this, Laba_4.class);
                startActivity(lab4Activity);
            }
    });
    }

    public void saveAndCleanUserData() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.USER_DATA_KEY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.FIRST_NAME_KEY, firstNameStr);
        editor.putString(Constants.LAST_NAME_KEY, lastNameStr);
        editor.putString(Constants.PHONE_KEY, phoneStr);
        editor.putString(Constants.EMAIL_KEY, emailStr);
        editor.apply();
        firstName.getText().clear();
        lastName.getText().clear();
        phone.getText().clear();
        email.getText().clear();
        password.getText().clear();
        cnfpass.getText().clear();
    }
}


