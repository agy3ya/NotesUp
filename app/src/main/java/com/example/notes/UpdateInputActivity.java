package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.Model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class UpdateInputActivity extends AppCompatActivity {
    private TextInputEditText titleUpdated;
    private EditText noteUpdated;
    private FloatingActionButton buttonUpdated;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView dateUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_input);
        mAuth=FirebaseAuth.getInstance();

        final FirebaseUser[] mUser = {mAuth.getCurrentUser()};
        final String[] uId = {mUser[0].getUid()};

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Notes").child(uId[0]);

        final String[] title = {getIntent().getStringExtra("Heading")};
        final String[] note = {getIntent().getStringExtra("Note")};
        String postKey=getIntent().getStringExtra("Id");
        String date =getIntent().getStringExtra("Date");
        titleUpdated=findViewById(R.id.my_heading_update);
        noteUpdated=findViewById(R.id.my_note_updated);
        dateUpdated=findViewById(R.id.my_date_updated);


        titleUpdated.setText(title[0]);
        titleUpdated.setSelection(title[0].length());

        noteUpdated.setText(note[0]);
        noteUpdated.setSelection(note[0].length());

        dateUpdated.setText(date);

        buttonUpdated=findViewById(R.id.update_button);
        buttonUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title[0] =titleUpdated.getText().toString().trim();
                note[0] =noteUpdated.getText().toString().trim();
                String mDate= DateFormat.getDateInstance().format(new Date());
                Data data = new Data(title[0], note[0],mDate,postKey);
                mDatabase.child(postKey).setValue(data);

                Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));

            }
        });

    }
}