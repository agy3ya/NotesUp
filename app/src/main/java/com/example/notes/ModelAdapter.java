package com.example.notes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModelAdapter extends FirebaseRecyclerAdapter<Data, ModelAdapter.MyViewHolder> {

    public ModelAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {
    /*    holder.mTitle.setText(model.getTitle());
        holder.mNote.setText(model.getNote());
        holder.mDate.setText(model.getDate());
    */
        holder.setTitle(model.getTitle());
        holder.setNote(model.getNote());
        holder.setDate(model.getDate());
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),UpdateInputActivity.class);
                view.getContext().startActivity(intent);

            }
        });
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);

        return new MyViewHolder(view);
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView mTitle,mNote,mDate;
          View myView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
           // TextView mTitle = itemView.findViewById(R.id.heading);
           // TextView mNote=itemView.findViewById(R.id.subnote);
           // TextView mDate= itemView.findViewById(R.id.my_date);
        }
        public void setTitle(String title){
            TextView mTitle = myView.findViewById(R.id.heading);
            mTitle.setText(title);
        }
        public void setNote(String note){
            TextView mNote=myView.findViewById(R.id.subnote);
            mNote.setText(note);
        }
        public void setDate(String date){
            TextView mDate=myView.findViewById(R.id.my_date);
            mDate.setText(date);
        }

    }
    
}
