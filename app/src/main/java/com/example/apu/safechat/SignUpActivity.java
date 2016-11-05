package com.example.apu.safechat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected Button signUpButton;
    private FirebaseAuth mFirebaseAuth;

    private String emailAddress;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }*/

    //for add email
    //private FirebaseAuth mFirebaseAuth2;
    //private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    //private String mUserId;
    //

    public String username(String Email)
    {
        final String TAG="SignUpActivity";
        //MainActivity
        //String email = ContactActivity.mAuth.getCurrentUser().getEmail();
        Log.i(TAG, "usernameOfCurrentUser(): "+Email);
        if (Email.contains("@")) {
            return Email.split("@")[0];
        } else {
            return Email;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mDatabase = FirebaseDatabase.getInstance().getReference();//for email setup

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        signUpButton = (Button)findViewById(R.id.signupButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                password = password.trim();
                email = email.trim();

                if (password.isEmpty() || email.isEmpty()) {
                    //
                    Context context = getApplicationContext();
                    CharSequence text = "Password or Email empty!!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    final String finalEmail = email;
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {



                                        //start :for add email
                                        // Initialize Firebase Auth and Database Reference
                                        //mFirebaseAuth2 = FirebaseAuth.getInstance();
                                        //mFirebaseUser = mFirebaseAuth2.getCurrentUser();
                                        //mDatabase = FirebaseDatabase.getInstance().getReference();
                                        // if (mFirebaseUser == null) {
                                        // Not logged in, launch the Log In activity
                                        //loadLogInView();
                                        // } else {
                                        //mUserId = mFirebaseUser.getUid();
                                        mDatabase.child("users").child("emails").push().child("email").setValue(username(finalEmail));
                                        Log.i("SignUpActivity","onComplete :"+finalEmail);
                                        //end


                                        //Toast
                                        Context context = getApplicationContext();
                                        CharSequence text = "Success ";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        //
                                        //Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        Intent intent = new Intent(SignUpActivity.this, NavigationActivity.class);
                                        intent.putExtra("SignUpEmail",username(finalEmail));
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }
                                    else {
                                        //
                                        Context context = getApplicationContext();
                                        CharSequence text = "Someting Error!!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        //
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }

                              /*  @Override
                        public void onComplete(@NonNull Task</AuthResult><AuthResult>task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle(R.string.login_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }*/
                    });
                }
            }
        });
    }

}

