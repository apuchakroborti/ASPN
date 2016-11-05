package com.example.apu.safechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabase;
    private String mUserId;

    private String userName;


    private void loadLogInView() {
        Intent intent = new Intent(MainActivity.this,LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();

            // Set up ListView
            final ListView listView = (ListView) findViewById(R.id.listView);
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            listView.setAdapter(adapter);

            // Add items via the Button and EditText at the bottom of the view.
            //final EditText text = (EditText) findViewById(R.id.todoText);
            //final Button button = (Button) findViewById(R.id.addButton);
            final String email = "";

            //getEmailfrom signup

            Intent i = getIntent();
            //The second parameter below is the default string returned if the value is not there.
            userName = i.getExtras().getString("SignUpEmail");

            //
/*
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                    //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                    //mDatabase.child("users").child(mUserId).child("items").push().child("title").setValue(text.getText().toString());
                    mDatabase.child("users").child(userName).child("messages").push().child("title").setValue(text.getText().toString());
                    text.setText("");
                }
            });*/

            // Use Firebase to populate the list.
            //mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
            //mDatabase.child("users").child(userName).child("messages").addChildEventListener(new ChildEventListener() {
            mDatabase.child("users").child("emails").addChildEventListener(new ChildEventListener() {
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

            //for contact selection
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("users").child("emails");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    Query myQuery = myRef.orderByValue().equalTo((String) listView.getItemAtPosition(position));

                    myQuery.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String contact = adapter.getItem(position).toString();
                            //mDatabase.child("users").child(contact).child("messages").push().child("title").setValue(text.getText().toString());

                            Intent intent = new Intent(MainActivity.this, SendMessages.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("choosenContact", contact);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);


                        /*if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }*/
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    })
                    ;
                }
            });


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            //return true;
            mFirebaseAuth.signOut();
            loadLogInView();
            return true;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
