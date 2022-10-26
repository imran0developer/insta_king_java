package com.unitapplications.InstaKing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.unitapplications.InstaKing.Adapters.CategoryAdapter;
import com.unitapplications.InstaKing.Models.CategoryModel;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView category_rv;
    CategoryAdapter categoryAdapter;
    private ArrayList<com.unitapplications.InstaKing.Models.CategoryModel> categoryModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        category_rv = findViewById(R.id.category_rv);
        categoryModelArrayList = new ArrayList<>();

        categoryModelArrayList.add(new CategoryModel("Attitude",R.drawable.ic_attitude));
        categoryModelArrayList.add(new CategoryModel("Single/Happy",R.drawable.ic_single));
        categoryModelArrayList.add(new CategoryModel("Love",R.drawable.ic_love));
        categoryModelArrayList.add(new CategoryModel("Sad",R.drawable.ic_sad));
        categoryModelArrayList.add(new CategoryModel("Funny",R.drawable.ic_laughter));
        categoryModelArrayList.add(new CategoryModel("Friends",R.drawable.frnds));
        categoryModelArrayList.add(new CategoryModel("Selfie",R.drawable.selfie));
init2();
    }
    public void init2(){
   GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this,2);
        //Collections.shuffle(categoryModelArrayList);
        categoryAdapter = new CategoryAdapter(categoryModelArrayList, CategoryActivity.this);
        category_rv.setLayoutManager(layoutManager);
        category_rv.setAdapter(categoryAdapter);
    }
}