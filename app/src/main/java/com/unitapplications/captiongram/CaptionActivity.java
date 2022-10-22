package com.unitapplications.captiongram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unitapplications.captiongram.Adapters.InstaAdapter;
import com.unitapplications.captiongram.Models.InstaModel;

import java.util.ArrayList;
import java.util.Collections;

public class CaptionActivity extends AppCompatActivity {
    InstaAdapter instaAdapter;
    RecyclerView caption_rv;
    FirebaseDatabase fbDb;
    String got_category_name;

    private ArrayList<InstaModel> instaModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption);

        caption_rv = findViewById(R.id.caption_layout);
        instaModelArrayList = new ArrayList<>();

        fbDb = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = fbDb.getReference();
        DatabaseReference getImg = DatabaseRef.child("Caption_gram/InstaModel");

        getImg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                instaModelArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InstaModel instaModel = dataSnapshot.getValue(InstaModel.class);
                    instaModelArrayList.add(instaModel);
                    init();
                }
                try {
                    Intent get_intent = getIntent();
                    got_category_name = get_intent.getStringExtra("category_name_key");
                    for(int i=0;i<2;i++){
                        filter(got_category_name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                instaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                msg("Check your Internet Connection");
            }
        });
        findViewById(R.id.imageView).setOnClickListener(view -> {
startActivity(new Intent(CaptionActivity.this,FavActivity.class));
        });
    }
    private void filter(String got_category_name1) {
        //msg(got_category_name1);
        // this is interesting method that filters arraylist
        //according to search

        ArrayList<InstaModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (InstaModel item : instaModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCaption().toLowerCase().contains(got_category_name1.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.

                filteredlist.add(0, item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            instaAdapter.filteringList(filteredlist);
        }
    }
    public void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        Collections.shuffle(instaModelArrayList);
        instaAdapter = new InstaAdapter(instaModelArrayList, CaptionActivity.this);
        caption_rv.setLayoutManager(layoutManager);
        caption_rv.setAdapter(instaAdapter);
    }
    public void msg(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}