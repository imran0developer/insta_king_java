package com.unitapplications.captiongram.Adapters;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import com.unitapplications.captiongram.MainActivity;
import com.unitapplications.captiongram.Models.InstaModel;
import com.unitapplications.captiongram.R;

import java.util.ArrayList;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {

    private static Context context;
    private ArrayList<InstaModel> instaModelArrayList;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.insta_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        String gotUsername = getUsername();
        InstaModel insta_model = instaModelArrayList.get(position);
      SpannableString final_str =  makeBoldCaption(gotUsername+" "+insta_model.getCaption());


    //    holder.caption_tv.setText(insta_model.getCaption());
        holder.caption_tv.setText(final_str);
        holder.more_less.setOnClickListener(view -> {
            ViewGroup.LayoutParams caption_tv_params = holder.caption_tv.getLayoutParams();
            caption_tv_params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.caption_tv.setLayoutParams(caption_tv_params);
            ViewGroup.LayoutParams params = holder.insta_layout.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.insta_layout.setLayoutParams(params);
            holder.more_less.setVisibility(View.GONE);
        });
        holder.comment.setOnClickListener(view -> {
            Toast.makeText(context, "Buddy this is not Instagram ha ha ha "+"\ud83d\ude0a", Toast.LENGTH_SHORT).show();
        });
        holder.like_iv.setOnClickListener(view -> {
            holder.like_count.setText("10,00,000 Likes");
            holder.like_iv.setImageResource(R.drawable.liked);
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("cap_hash", insta_model.getCaption());
            clipboard.setPrimaryClip(clip);
          Toast.makeText(context, "Caption & Hashtags are Copied "+"\u2713", Toast.LENGTH_LONG).show();
        });


    }
    public void filteringList(ArrayList<InstaModel> allFilteredCaptions) {
        instaModelArrayList = allFilteredCaptions;

        notifyDataSetChanged();
        // i don't know about this notify method i just did according
        // to tutorial of search view
        //18 OCt 2022 now i know about notify method
        //this method notify the adapter that data is changed hehehe
    }
    public static int getUsernameLenght(){
        SharedPreferences sharedPref = context.getSharedPreferences(MainActivity.PREF, MODE_PRIVATE);
        String value = sharedPref.getString(MainActivity.USERNAME, "user_name");
        int un_length = value.length();
        return un_length;
    }
    public static String getUsername(){
        SharedPreferences sharedPref = context.getSharedPreferences(MainActivity.PREF, MODE_PRIVATE);
        String value = sharedPref.getString(MainActivity.USERNAME, "user_name");
        return value;
    }
    public static SpannableString makeBoldCaption(String get_caption){
        int lenght = getUsernameLenght();
        SpannableString ss = new SpannableString(get_caption);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, lenght, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return instaModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView caption_tv,more_less,like_count;
        private ImageView like_iv,save_iv,comment,share;
        private LinearLayout insta_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            caption_tv = itemView.findViewById(R.id.caption_tv);
            like_iv = itemView.findViewById(R.id.like_iv);
            comment = itemView.findViewById(R.id.comment_iv);
            share = itemView.findViewById(R.id.share_iv);
            save_iv = itemView.findViewById(R.id.save_iv);
            more_less = itemView.findViewById(R.id.more_less);
            insta_layout = itemView.findViewById(R.id.insta_layout);
            like_count = itemView.findViewById(R.id.like_count);

        }
    }
}