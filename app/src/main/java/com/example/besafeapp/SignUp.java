package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.besafeapp.helpers.DBHelper;

public class SignUp extends AppCompatActivity {

    Button proceedButton;
    EditText phoneNoEditText, nameEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        proceedButton = findViewById(R.id.proceedButton);
        phoneNoEditText = findViewById(R.id.phonenoEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, VerifyNumber.class);
                intent.putExtra("phoneNo", phoneNoEditText.getText().toString());
                intent.putExtra("name", nameEditText.getText().toString());
                intent.putExtra("password", passwordEditText.getText().toString());

                DBHelper DB = new DBHelper(getApplicationContext());
                if(DB.isOldUser(phoneNoEditText.getText().toString()) == true){
                    Toast.makeText(SignUp.this, "User already registered. Login!", Toast.LENGTH_SHORT).show();
                    System.out.println(DB.isOldUser(phoneNoEditText.getText().toString()));
                }
                else{
                    startActivity(intent);
                }
            }
        });

    }
}