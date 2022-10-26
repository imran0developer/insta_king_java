package com.unitapplications.InstaKing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unitapplications.InstaKing.Adapters.NoteAdapter;
import com.unitapplications.InstaKing.Models.NoteModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity {
    FirebaseDatabase fbDb;
    RecyclerView note_rv;
    ArrayList<NoteModel> noteModelArrayList;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Objects.requireNonNull(getSupportActionBar()).hide();

        note_rv = findViewById(R.id.note_rv);
        fbDb = FirebaseDatabase.getInstance();
        noteModelArrayList = new ArrayList<>();

        DatabaseReference DatabaseRef = fbDb.getReference();
        DatabaseReference getImg = DatabaseRef.child("Caption_gram/NoteModel/NoteModel");

        getImg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NoteModel noteModel = dataSnapshot.getValue(NoteModel.class);
                    noteModelArrayList.add(noteModel);
                    init3();
                }
              }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                msg("Check your Internet Connection");
            }
        });

    }
    public void init3(){
        GridLayoutManager layoutManager = new GridLayoutManager(NoteActivity.this, 2);
        Collections.shuffle(noteModelArrayList);
        noteAdapter = new NoteAdapter(noteModelArrayList, NoteActivity.this);
        note_rv.setLayoutManager(layoutManager);
        note_rv.setAdapter(noteAdapter);
    }
    public void msg(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}