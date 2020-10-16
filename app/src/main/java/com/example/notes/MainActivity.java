package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.net.Inet4Address;

public class   MainActivity extends AppCompatActivity {
    private TextView signup;
    private TextView signupBold;
    private TextInputEditText email;
    private TextInputEditText pass;
    private MaterialButton materialButton;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
        mDialog=new ProgressDialog(this);

        signup= findViewById(R.id.reg_text_view1);
        signupBold=findViewById(R.id.reg_text_view2);
        email=findViewById(R.id.login_email );
        pass=findViewById(R.id.login_password);
        materialButton=findViewById(R.id.login_button);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String mEmail = email.getText().toString().trim();
              String mPass = pass.getText().toString().trim();
              if(TextUtils.isEmpty(mEmail)){
                  email.setError("Required Field");
                  return;
              }
              if(TextUtils.isEmpty(mPass)){
                  pass.setError("Required Field");
                  return;
              }
              mDialog.setMessage("Processing");
              mDialog.show();
              mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                      mDialog.dismiss();
                  }
                  else{
                      Toast.makeText(getApplicationContext(),"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                      mDialog.dismiss();
                  }
                  }
              });


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));

            }
        });
        signupBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });



    }


}