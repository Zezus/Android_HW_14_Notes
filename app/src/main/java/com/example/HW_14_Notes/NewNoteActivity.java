package com.example.HW_14_Notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class NewNoteActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        editText = findViewById(R.id.edit_text);
    }

    public void addNote(View view) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "\n" +
                    "Note can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String filename = "notesBase";
        String fileContents = editText.getText().toString() + "EON";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Note created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
