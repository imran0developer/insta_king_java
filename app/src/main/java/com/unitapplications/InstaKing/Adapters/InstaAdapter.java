package com.unitapplications.InstaKing.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitapplications.InstaKing.Data.favDbHandler;

import com.unitapplications.InstaKing.Models.FavModel;
import com.unitapplications.InstaKing.Models.InstaModel;
import com.unitapplications.InstaKing.R;

import java.util.ArrayList;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {

    private static Context context;
    private ArrayList<InstaModel> instaModelArrayList;
    favDbHandler favDbHandler;
//    private Context context;

    // constructor
    public InstaAdapter(ArrayList<InstaModel> instaModelArrayList, Context context) {
        this.instaModelArrayList = instaModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.caption_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.

        InstaModel insta_model = instaModelArrayList.get(position);
        holder.caption_tv.setText(insta_model.getCaption()+"\n.\n.\n"+context.getResources().getString(R.string.hashtags)
                +"\n caption by @crazy0imran");
        holder.more.setOnClickListener(view -> {
            ViewGroup.LayoutParams params  =  holder.caption_tv.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.caption_tv.setLayoutParams(params);
            holder.more.setVisibility(View.GONE);
        });
        holder.like_iv.setOnClickListener(view -> {
            holder.like_iv.setImageResource(R.drawable.liked);
          favDbHandler = new favDbHandler(context);
            FavModel favModel = new FavModel();
            favModel.setFav_caption(insta_model.getCaption());
          favDbHandler.addFav(favModel);
        });
        holder.copy_iv.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("cap_hash", insta_model.getCaption());
            clipboard.setPrimaryClip(clip);
          Toast.makeText(context, "Caption & Hashtags are Copied "+"\u2713", Toast.LENGTH_LONG).show();
        });


    }
    @SuppressLint("NotifyDataSetChanged")
    public void filteringList(ArrayList<InstaModel> allFilteredCaptions) {
        instaModelArrayList = allFilteredCaptions;

        notifyDataSetChanged();
        // i don't know about this notify method i just did according
        // to tutorial of search view
        //18 OCt 2022 now i know about notify method
        //this method notify the adapter that data is changed hehehe
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return instaModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView caption_tv;
        private ImageView like_iv,copy_iv,more;
        private LinearLayout insta_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            caption_tv = itemView.findViewById(R.id.caption_tv);
            like_iv = itemView.findViewById(R.id.like_iv);
            copy_iv = itemView.findViewById(R.id.copy_iv);
            more = itemView.findViewById(R.id.more);


        }
    }
}