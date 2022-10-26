package com.unitapplications.InstaKing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    CardView caption_cv,note_cv;
    TextView caption_bt_tv , note_bt_tv;
    ImageView fav_iv,share_iv,rate_us_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        caption_cv = findViewById(R.id.caption_cv);
        note_cv = findViewById(R.id.note_cv);
        caption_bt_tv = findViewById(R.id.caption_button);
        note_bt_tv = findViewById(R.id.note_button);

        fav_iv = findViewById(R.id.fav_iv);
        rate_us_iv = findViewById(R.id.rate_us);
        share_iv = findViewById(R.id.share_iv);


        caption_cv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,CaptionActivity.class));
        });
        caption_bt_tv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,CaptionActivity.class));
        });
        note_cv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, com.unitapplications.InstaKing.NoteActivity.class));
        });
        note_bt_tv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, com.unitapplications.InstaKing.NoteActivity.class));
        });
        share_iv.setOnClickListener(view -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);

            // type of the content to be shared
            sharingIntent.setType("text/plain");

            // Body of the content
            String shareBody = "https://play.google.com/store/apps/details?id="+getPackageName();

            // subject of the content. you can share anything
            String shareSubject = "Share app with your Friends";

            // passing body of the content
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

            // passing subject of the content
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share app using.."));
        });
        findViewById(R.id.fav_iv).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, com.unitapplications.InstaKing.FavActivity.class));
        });
        findViewById(R.id.rate_us).setOnClickListener(view -> {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
        });

    }


    public void msg(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}