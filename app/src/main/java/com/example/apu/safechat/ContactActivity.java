package com.example.apu.safechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.id.text1;

public class ContactActivity extends AppCompatActivity {

   // private FirebaseAuth mFirebaseAuth;
    //private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabase;
    private String mUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Initialize Firebase Auth and Database Reference
        //mFirebaseAuth = FirebaseAuth.getInstance();
        //mFirebaseUser = mFirebaseAuth.getCurrentUser();
        //mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // if (mFirebaseUser == null) {
        // Not logged in, launch the Log In activity
        //loadLogInView();
        // } else {
        //mUserId = mFirebaseUser.getUid();

        // Set up ListView
        ListView listView = (ListView) findViewById(R.id.lvContact);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, R.id.lvContact);
        listView.setAdapter(adapter);

        // Add items via the Button and EditText at the bottom of the view.
        //final EditText text = (EditText) findViewById(R.id.todoText);
        //final Button button = (Button) findViewById(R.id.addButton);
        //final String email="";

        /*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                //mDatabase.child("users").child(mUserId).child("items").push().child("title").setValue(text.getText().toString());
                mDatabase.child("users").child("apu_chakroborti@yahoo.com").push().child("title").setValue(text.getText().toString());
                text.setText("");
            }
        });*/

        // Use Firebase to populate the list.
        //mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
        mDatabase.child("users").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add((String) dataSnapshot.child("email").getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("email").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //  }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            return true;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }*/
}


/*
public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "ContactActivity" ;
    private static FirebaseAuth mAuth;

    private DatabaseReference userlistReference;
    private ValueEventListener mUserListListener;
    private ArrayList<String> usernamelist = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    private ListView userListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //mAuth.getCurrentUser();//get current user
        userListView = (ListView) findViewById(R.id.lvContact);
        userlistReference = FirebaseDatabase.getInstance().getReference().child("users");
        //onStart();

        //for

    }

    @Override
    protected void onStart() {
        super.onStart();
        final ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usernamelist = new ArrayList<>((ArrayList) dataSnapshot.getValue());
                usernamelist.remove(usernameOfCurrentUser());

                Log.i(TAG, "onDataChange: "+usernamelist.toString());
                arrayAdapter = new ArrayAdapter(ContactActivity.this,android.R.layout.simple_list_item_1,usernamelist);
                //arrayAdapter = new ArrayAdapter(ContactActivity.this,android.R.layout.simple_list_item_1,R.id.lvContact);
                userListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ",databaseError.toException());
                Toast.makeText(ContactActivity.this, "Failed to load User list.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        userlistReference.addValueEventListener(userListener);
        mUserListListener = userListener;
    }


    public String usernameOfCurrentUser()
    {
        //MainActivity
        String email = ContactActivity.mAuth.getCurrentUser().getEmail();
        Log.i(TAG, "usernameOfCurrentUser(): "+email);
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mUserListListener != null) {
            userlistReference.removeEventListener(mUserListListener);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ContactActivity.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
*/