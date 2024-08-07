package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.besafeapp.helpers.DBHelper;

public class EditAccount extends AppCompatActivity {

    String spinner_items[] = {"Enabled", "Disabled"};
    EditText nameText, phoneText, passText, sosText;
    Spinner spinner;
    Button modify, save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("account");
        }

        nameText = findViewById(R.id.name1EditText);
        phoneText = findViewById(R.id.phoneno1EditText);
        passText = findViewById(R.id.password1EditText);
        sosText = findViewById(R.id.sosEditText);

        spinner = findViewById(R.id.discreteToggle);
        ArrayAdapter items = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinner_items);
        items.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(items);
        //spinner.setSelection(5);

        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phoneNo = sp.getString("phoneNo", "");

        DBHelper DB = new DBHelper(getApplicationContext());
        Cursor user = DB.retrieveUser(phoneNo);
        if(user.getCount()>0){
            while(user.moveToNext()){
                nameText.setText(user.getString(1));
                phoneText.setText(user.getString(0));
                phoneText.setEnabled(false);
                sosText.setText(user.getString(3));
                if(user.getString(4).equals("Enabled")) spinner.setSelection(0);
                else spinner.setSelection(1);
            }
        }
        else{
            Toast.makeText(this, "Error Retrieving", Toast.LENGTH_SHORT).show();
        }

        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneText.getText().toString();
                DB.updateName(phone, nameText.getText().toString());
                DB.updateSosNumber(phone, sosText.getText().toString());
                DB.updateDiscrete(phone, spinner.getSelectedItem().toString());

//                if(!passText.getText().equals("")){
//                    DB.updatePassword(phone, passText.getText().toString());
//                }
                goBack();
            }
        });

        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        modify = findViewById(R.id.modButton);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAccount.this, AddEmergNo.class);
                startActivity(intent);
            }
        });
    }

    void goBack(){
        Intent intent = new Intent(EditAccount.this, AccountSettings.class);
        startActivity(intent);
    }
}