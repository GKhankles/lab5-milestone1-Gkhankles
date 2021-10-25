package com.example.lab5_milestone1_gkhankles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("lab5_1", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            goToActivity2(sharedPreferences.getString(usernameKey, ""));
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void clickFunc(View view) {
        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName);
        String str = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("lab5_1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();
        goToActivity2(str);
    }

    public void goToActivity2(String s) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}