package com.unitapplications.InstaKing.Adapters;



import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitapplications.InstaKing.Models.NoteModel;
import com.unitapplications.InstaKing.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static Context context;
    private ArrayList<NoteModel> noteModelArrayList;

    // constructor
    public NoteAdapter(ArrayList<NoteModel> noteModelArrayList, Context context) {
        this.noteModelArrayList = noteModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.

        NoteModel noteModel = noteModelArrayList.get(position);
        //holder.like_iv.setImageResource(R.drawable.liked);
        holder.note_tv.setText(noteModel.getNote());

/*        holder.like_iv.setOnClickListener(view -> {
            //   holder.like_iv.setImageResource(R.drawable.not_liked);
        });*/
        holder.copy_iv_note.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("cap_hash", noteModel.getNote());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Note is Copied "+"\u2713 "+noteModel.getNote(), Toast.LENGTH_LONG).show();
        });


    }
    @Override
    public int getItemCount() {
        // returning the size of our array list
        return noteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView note_tv;
        private ImageView copy_iv_note;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_tv = itemView.findViewById(R.id.note_tv);
            copy_iv_note = itemView.findViewById(R.id.copy_iv_note);


        }
    }
}