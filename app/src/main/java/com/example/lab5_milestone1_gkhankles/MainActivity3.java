package com.example.lab5_milestone1_gkhankles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    TextView textView2;
    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView2 = (TextView) findViewById(R.id.editTextTextPersonName3);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            textView2.setText(noteContent);
        }
    }

    public void saveText(View view) {
        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName3);
        String content = myTextField.getText().toString();
        Context context = getApplicationContext();
        sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("lab5_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}