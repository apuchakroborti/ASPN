package com.example.apu.safechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class FragmentChat1 extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabase;


    private String userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_send_messages, container, false);

        // Initialize Firebase Auth and Database Reference
         mFirebaseAuth = FirebaseAuth.getInstance();
         mFirebaseUser = mFirebaseAuth.getCurrentUser();
         //mFirebaseUser = mFirebaseAuth.getCurrentUser();
         mDatabase = FirebaseDatabase.getInstance().getReference();
        //

            // Set up ListView
            final ListView listView = (ListView) rootView.findViewById(R.id.listViewSend);
            //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1 , android.R.id.text1);
        listView.setAdapter(adapter);
         final String email = "";

            //getEmailfrom signup

            //Intent i = getIntent();
            //The second parameter below is the default string returned if the value is not there.
            //userName = i.getExtras().getString("SignUpEmail");
            //userName="apu";

        //userName=username(mFirebaseUser.toString());
        userName="apu";
            //
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

                            Intent intent = new Intent(getActivity().getApplication(), SendMessages.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("choosenContact", contact);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    })
                    ;
                }
            });

            return rootView;
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

}
