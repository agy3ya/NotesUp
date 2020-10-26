package com.example.notes;

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
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ModelAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {
        holder.mTitle.setText(model.getTitle());
        holder.mNote.setText(model.getNote());
        holder.mDate.setText(model.getDate());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);

        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle,mNote,mDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView mTitle = itemView.findViewById(R.id.title);
            TextView mNote=itemView.findViewById(R.id.note);
            TextView mDate= itemView.findViewById(R.id.my_date);




        }
    }
    
}
