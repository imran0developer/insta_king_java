package com.unitapplications.InstaKing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unitapplications.InstaKing.Adapters.InstaAdapter;
import com.unitapplications.InstaKing.Models.InstaModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CaptionActivity extends AppCompatActivity {
    InstaAdapter instaAdapter;
    RecyclerView caption_rv;
    FirebaseDatabase fbDb;
    SearchView searchView;
    ImageView fav_bt,cat_bt;



    private ArrayList<InstaModel> instaModelArrayList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CaptionActivity.this,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption);
        Objects.requireNonNull(getSupportActionBar()).hide();

        msg("Please Wait...");
        searchView = findViewById(R.id.search_bar);
        fav_bt = findViewById(R.id.fav_iv2);
        cat_bt = findViewById(R.id.category_iv_bt);

        caption_rv = findViewById(R.id.caption_layout);
        instaModelArrayList = new ArrayList<>();


        fbDb = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = fbDb.getReference();
        DatabaseReference getImg = DatabaseRef.child("Caption_gram/InstaModel/InstaModel");

        getImg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                instaModelArrayList.clear();


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InstaModel instaModel = dataSnapshot.getValue(InstaModel.class);
                    instaModelArrayList.add(instaModel);
                }

                init();
                try {
                    Intent getI = getIntent();
                    String got_name = getI.getStringExtra("category_name_key");
                    switch (got_name){
                        case "Attitude":{
                                filter("$");
                        }
                        break;
                        case "Single/Happy":{
                            filter("(");
                        }
                        break;
                        case "Love":{
                            filter(")");
                        }
                        break;
                        case "Sad":{
                            filter("*");
                        }
                        break;
                        case "Funny":{
                            filter("^");
                        }break;
                        case "Friends":{
                            filter("~");
                        }break;
                        case "Selfie":{
                            filter("!");
                        }break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                msg("Check your Internet Connection");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }

        });

    fav_bt.setOnClickListener(view -> {
        startActivity(new Intent(CaptionActivity.this, com.unitapplications.InstaKing.FavActivity.class));
    });
        cat_bt.setOnClickListener(view -> {
            startActivity(new Intent(CaptionActivity.this, CategoryActivity.class));
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