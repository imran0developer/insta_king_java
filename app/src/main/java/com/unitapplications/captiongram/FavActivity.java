package com.unitapplications.captiongram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.unitapplications.captiongram.Adapters.CategoryAdapter;
import com.unitapplications.captiongram.Adapters.FavAdapter;
import com.unitapplications.captiongram.Data.favDbHandler;
import com.unitapplications.captiongram.Models.FavModel;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {
    FavAdapter favAdapter;
   public static ArrayList<FavModel> favModelArrayList;
    RecyclerView fav_rv;
    favDbHandler favDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
    favModelArrayList = new ArrayList<>();
    favDbHandler = new favDbHandler(this);
        fav_rv = findViewById(R.id.fav_rv);
       /* try {
            Intent fav_intent = getIntent();
            String got_fav_cap = fav_intent.getStringExtra("fav_cap");
        favModelArrayList.add(new FavModel(got_fav_cap));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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