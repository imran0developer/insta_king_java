package com.unitapplications.InstaKing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.unitapplications.InstaKing.Adapters.FavAdapter;
import com.unitapplications.InstaKing.Data.favDbHandler;
import com.unitapplications.InstaKing.Models.FavModel;

import java.util.ArrayList;
import java.util.Objects;

public class FavActivity extends AppCompatActivity {
    FavAdapter favAdapter;
   public static ArrayList<FavModel> favModelArrayList;
    RecyclerView fav_rv;
    favDbHandler favDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Objects.requireNonNull(getSupportActionBar()).hide();

    favModelArrayList = new ArrayList<>();
    favDbHandler = new favDbHandler(this);
        fav_rv = findViewById(R.id.fav_rv);

      setRecyclerView();
    }
    public void setRecyclerView() {
        // here i made layout manager and arraylist from database
        ArrayList<FavModel> fav_caption = favDbHandler.getAllFAv();
        for (FavModel model : fav_caption) {
            // it starts the loop and populate the arraylist from database

            //this is just a log for debug purpose
            log("Id: " + model.getId() +
                    "\nfav is  : " + model.getFav_caption());
            //this add to arraylist
            //the zero in this is for indexing the recent
            //to be on top
            favModelArrayList.add(0, model);

        }
        //here view set adapter and layout manager to recycler view
        init();
    }
    public void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        //Collections.shuffle(categoryModelArrayList);
        favAdapter = new FavAdapter(favModelArrayList,FavActivity.this);
        fav_rv.setLayoutManager(layoutManager);
        fav_rv.setAdapter(favAdapter);
    }
    public static void newFav(String fav_cap){
       favModelArrayList.add(new FavModel(fav_cap));
    }
    public void log(String l){
        Log.d("taggi",l);

    }
}