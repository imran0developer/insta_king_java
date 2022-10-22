package com.unitapplications.captiongram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;
import com.unitapplications.captiongram.Adapters.CategoryAdapter;

import com.unitapplications.captiongram.Models.CategoryModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CategoryAdapter categoryAdapter;
    private ArrayList<CategoryModel> categoryModelArrayList;
    RecyclerView category_rv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        button= findViewById(R.id.button);
        category_rv = findViewById(R.id.category_rv);
        categoryModelArrayList = new ArrayList<>();

        categoryModelArrayList.add(new CategoryModel("Attitude"));
        categoryModelArrayList.add(new CategoryModel("Happy"));
        categoryModelArrayList.add(new CategoryModel("Sad"));
        categoryModelArrayList.add(new CategoryModel("Angry"));

button.setOnClickListener(view -> {
startActivity(new Intent(MainActivity.this,CaptionActivity.class));
});
        init();
    }

    public void init(){
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
     /*   LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);*/
        //Collections.shuffle(categoryModelArrayList);
        categoryAdapter = new CategoryAdapter(categoryModelArrayList,MainActivity.this);
        category_rv.setLayoutManager(layoutManager);
        category_rv.setAdapter(categoryAdapter);
    }
    public void msg(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    public void hideActionBar() {
        // try block to hide Action bar
        try {this.getSupportActionBar().hide();} catch (NullPointerException e) {}
    }

}