package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.Model.Data;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;


public class NoteActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes").child(uId);

        TextInputEditText title = findViewById(R.id.my_heading);
        EditText note = findViewById(R.id.my_note);
        FloatingActionButton saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTitle = title.getText().toString().trim();
                String mNote = note.getText().toString().trim();

                if(TextUtils.isEmpty(mTitle)){
                    title.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(mNote)){
                    note.setError("Required Field");
                    return;
                }
                String id = mDatabase.push().getKey();
              //  String date= DateFormat.getInstance().format(new Date());
               // String date = DateFormat.getInstance().format(new Date());
                String date = DateFormat.getDateInstance().format(new Date());

                Data data = new Data(mTitle,mNote,date,id);
                mDatabase.child(id).setValue(data);
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),HomeActivity.class));


            }
        });

    }



}