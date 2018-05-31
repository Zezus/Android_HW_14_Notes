package com.example.HW_14_Notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notes;
    private NotesAdapter notesAdapter;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] files = fileList();
        filename = "notesBase";
        boolean fileExists = false;
        FileInputStream fileInputStream;
        String notesString = "";

        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(filename)) {
                fileExists = true;
            }
        }
        if (!fileExists) {
            File file = new File(this.getFilesDir(), filename);
        }

        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                notesString += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] notesStringArray = notesString.split("EON");

        notes = new ArrayList<>();

        for (int i = 0; i < notesStringArray.length; i++) {
            Note note = new Note();
            note.setNoteText(notesStringArray[i]);
            notes.add(note);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        notesAdapter = new NotesAdapter(getApplicationContext(), notes);
        recyclerView.setAdapter(notesAdapter);
    }

    public void addNewNote(View view) {
        startActivity(new Intent(getApplicationContext(), NewNoteActivity.class));
    }
}
