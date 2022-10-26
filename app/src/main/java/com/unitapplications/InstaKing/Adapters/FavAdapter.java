package com.unitapplications.InstaKing.Adapters;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.unitapplications.InstaKing.Models.FavModel;
import com.unitapplications.InstaKing.R;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private static Context context;
    private ArrayList<FavModel> favModelArrayList;

    // constructor
    public FavAdapter(ArrayList<FavModel> favModelArrayList, Context context) {
        this.favModelArrayList = favModelArrayList;
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

        FavModel favModel = favModelArrayList.get(position);
        holder.like_iv.setImageResource(R.drawable.liked);
        holder.caption_tv.setText(favModel.getFav_caption());

        holder.like_iv.setOnClickListener(view -> {
         //   holder.like_iv.setImageResource(R.drawable.not_liked);
        });
        holder.copy_iv.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("cap_hash", favModel.getFav_caption());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Caption & Hashtags are Copied "+"\u2713", Toast.LENGTH_LONG).show();
        });
        holder.more.setOnClickListener(view -> {
            ViewGroup.LayoutParams params  =  holder.caption_tv.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.caption_tv.setLayoutParams(params);
            holder.more.setVisibility(View.GONE);
        });


    }
    @Override
    public int getItemCount() {
        // returning the size of our array list
        return favModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView caption_tv;
        private ImageView like_iv,copy_iv,more;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption_tv = itemView.findViewById(R.id.caption_tv);
            like_iv = itemView.findViewById(R.id.like_iv);
            copy_iv = itemView.findViewById(R.id.copy_iv);
            more = itemView.findViewById(R.id.more);


        }
    }
}