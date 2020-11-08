package com.example.notes;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notes.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModelAdapter extends FirebaseRecyclerAdapter<Data, ModelAdapter.MyViewHolder> {
    private String post_key;
    private String date;
    private String title;
    private String note;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



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
        post_key = getRef(position).getKey();
        title=model.getTitle();
        note=model.getNote();
        date=model.getDate();


        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent intent = new Intent(view.getContext(),UpdateInputActivity.class);
                view.getContext().startActivity(intent);

            */


                updateData(view);
            }
        });

        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth= FirebaseAuth.getInstance();
                FirebaseUser mUser = mAuth.getCurrentUser();
                String uId = mUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes").child(uId);
                try{
                    mDatabase.child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
                }

                catch (IndexOutOfBoundsException e){
                    Log.i("removing element","Deleting too fast");
                }

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

        ImageView trash;
          View myView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myView=itemView;

            trash=itemView.findViewById(R.id.trash);
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
    public  void updateData(View view){
        Intent intent = new Intent(view.getContext(),UpdateInputActivity.class);
        intent.putExtra("Heading",title);
        intent.putExtra("Note",note);
        intent.putExtra("Id",post_key);
        intent.putExtra("Date",date);
        view.getContext().startActivity(intent);



    }
    
}
