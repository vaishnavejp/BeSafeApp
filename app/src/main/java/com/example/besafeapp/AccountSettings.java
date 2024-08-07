package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.besafeapp.helpers.DBHelper;

public class AccountSettings extends AppCompatActivity {

    TextView nameEdit, phoneEdit, passwordEdit, sosEdit, messageEdit, discEdit;

    Button editAccount, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("account");
        }

        nameEdit = findViewById(R.id.nameSettings);
        phoneEdit = findViewById(R.id.phoneNoSettings);
        passwordEdit = findViewById(R.id.passwordSettings);
        sosEdit = findViewById(R.id.sosSettings);
        discEdit = findViewById(R.id.discreteSettings);
        messageEdit = findViewById(R.id.messageSettings);
        editAccount = findViewById(R.id.editAccountbutton);
        logout = findViewById(R.id.logoutButton);

        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phone = sp.getString("phoneNo", "");

        DBHelper DB = new DBHelper(getApplicationContext());
        Cursor details = DB.retrieveUser(phone);
        System.out.println(details.getCount());

        if(details.getCount()>0){
            while (details.moveToNext()){
                nameEdit.setText(details.getString(1));
                phoneEdit.setText(details.getString(0));
                passwordEdit.setText("************");
                sosEdit.setText(details.getString(3));
                discEdit.setText(details.getString(4));
                messageEdit.setText(details.getString(9)+" numbers");
            }
        }

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettings.this, EditAccount.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("logged", MODE_PRIVATE);
                sp.edit().putBoolean("logged",false).apply();

                sp=getSharedPreferences("userID", MODE_PRIVATE);
                sp.edit().putString("userID", "").apply();
                Intent intent = new Intent(AccountSettings.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}