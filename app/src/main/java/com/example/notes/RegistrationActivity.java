package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText pass;
    private TextInputEditText confirmPass;
    private MaterialButton buttonReg;
    private TextView login_text;
    private TextView login_text2;
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);
        confirmPass=findViewById(R.id.confirm_password_reg);

        buttonReg=findViewById(R.id.reg_button);
        login_text =findViewById(R.id.login_text_view1);
        login_text2=findViewById(R.id.login_text_view2);

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),MainActivity.class));
            }
        });
        login_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPass= pass.getText().toString().trim();
                String cPass=confirmPass.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(mPass)){
                    pass.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(cPass)){
                    confirmPass.setError("Required Field");
                    return;
                }
                if(mPass.equals(cPass)) {
                    mDialog.setMessage("Processing");
                    mDialog.show();
                    mAuth.createUserWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                mDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_LONG).show();
                                mDialog.dismiss();
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "Password & Confirm Password don't match", Toast.LENGTH_LONG).show();
                }



            }
        });


    }
}