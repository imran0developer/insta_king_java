package com.unitapplications.captiongram.Adapters;

import static android.content.Context.CAPTIONING_SERVICE;
import static android.content.Context.MODE_PRIVATE;

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

import com.unitapplications.captiongram.CaptionActivity;
import com.unitapplications.captiongram.MainActivity;
import com.unitapplications.captiongram.Models.CategoryModel;
import com.unitapplications.captiongram.Models.InstaModel;
import com.unitapplications.captiongram.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static Context context;
    private ArrayList<CategoryModel> categoryModelArrayList;

    // constructor
    public CategoryAdapter(ArrayList<CategoryModel> categoryModelArrayList, Context context) {
        this.categoryModelArrayList = categoryModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.

        CategoryModel categoryModel = categoryModelArrayList.get(position);

        holder.catergory_tv.setText(categoryModel.getCatergory_name());
        holder.category_iv.setOnClickListener(view -> {
            Intent new_intent = new Intent(context, CaptionActivity.class);
            new_intent.putExtra("category_name_key",categoryModel.getCatergory_name());
            context.startActivity(new_intent);
            Toast.makeText(context, "name is "+categoryModel.getCatergory_name() , Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public int getItemCount() {
        // returning the size of our array list
        return categoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView catergory_tv;
        private ImageView category_iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_iv = itemView.findViewById(R.id.category_iv);
            catergory_tv = itemView.findViewById(R.id.category_tv);

        }
    }
}