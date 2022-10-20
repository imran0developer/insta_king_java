package com.unitapplications.captiongram;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unitapplications.captiongram.Adapters.InstaAdapter;
import com.unitapplications.captiongram.Models.InstaModel;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static String PREF = "shared_pref";
    public static String USERNAME = "username";

    InstaAdapter instaAdapter;
    private ArrayList<InstaModel> instaModelArrayList;
    RecyclerView insta_rv;
    FirebaseDatabase fbDb;
    Button bt;
    Uri selectedImg;
    Button button;
    SharedPreferences sharedPref;
    TextView username_tv;

    ImageView upload_pic,dp;

    ActivityResultLauncher<String[]> resultLauncher;
    ActivityResultLauncher<Intent> GetImage;
    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        button= findViewById(R.id.button);
        upload_pic = findViewById(R.id.upload_pic_iv);
        username_tv = findViewById(R.id.username_tv);
        dp = findViewById(R.id.dp_iv);



        insta_rv = findViewById(R.id.insta_rv);
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
                instaAdapter.notifyDataSetChanged();
                msg("Loaded Images Succesfully");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                msg("Check your Internet Connection");

            }
        });

        upload_pic.setOnClickListener(view -> {
            filter();
           /* Intent intent = new Intent(
                    Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);*/
        });
        button.setOnClickListener(view -> {
            EditText et_username = findViewById(R.id.editTextTextPersonName);
            String username_str = et_username.getText().toString();
            username_tv.setText(username_str);

            sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USERNAME, username_str);
            editor.apply();
            init();
        });

        init();
    }
    private void filter() {
        // this is interesting method that filters arraylist
        //according to search

        ArrayList<InstaModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (InstaModel item : instaModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCaption().toLowerCase().contains("1".toLowerCase())) {
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
                this, LinearLayoutManager.HORIZONTAL, false);
        Collections.shuffle(instaModelArrayList);
        instaAdapter = new InstaAdapter(instaModelArrayList, MainActivity.this);
        insta_rv.setLayoutManager(layoutManager);
        insta_rv.setAdapter(instaAdapter);
    }
    public void msg(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    public void hideActionBar() {
        // try block to hide Action bar
        try {this.getSupportActionBar().hide();} catch (NullPointerException e) {}
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            selectedImg = data.getData();
            String filePath;
            if (selectedImg != null && "content".equals(selectedImg.getScheme())) {
                Cursor cursor = this.getContentResolver().query(selectedImg, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                cursor.moveToFirst();
                filePath = cursor.getString(0);
                cursor.close();
            } else {
                filePath = selectedImg.getPath();
            }

            //imageView.setImageURI(selectedImg);

            Picasso.get()
                    .load(selectedImg)
                    .resize(upload_pic.getWidth(), upload_pic.getHeight())
                    .placeholder(R.drawable.happy)
                    .into(upload_pic);
        }
    }
}