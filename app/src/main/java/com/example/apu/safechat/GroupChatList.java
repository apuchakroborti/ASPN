package com.example.apu.safechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

/**
 * Created by apuchakroborti on 11/5/2016.
 */

public class GroupChatList extends Activity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_list);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //
        // Set up ListView
        final ListView listView = (ListView) findViewById(R.id.listViewGroupList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        //mDatabase.child("users").child("emails").push().child("email").setValue(username(finalEmail));
        //mDatabase.child("users").child("emails").addChildEventListener(new ChildEventListener() {

        //mDatabase.child("users").child("GroupNames").child(gn).child("messages").push().child("title").setValue(" ");
        //mDatabase.child("users").child("GroupNames1").push().child("name").setValue(gn);

        //mDatabase.child("users").child("GroupNames").addChildEventListener(new ChildEventListener() {

        mDatabase.child("users").child("GroupNames1").addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add((String) dataSnapshot.child("name").getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("name").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //for contact selection
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users").child("GroupNames1");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Query myQuery = myRef.orderByValue().equalTo((String) listView.getItemAtPosition(position));

                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String GroupContact = adapter.getItem(position).toString();
                        //mDatabase.child("users").child(contact).child("messages").push().child("title").setValue(text.getText().toString());
                        Intent intent = new Intent(GroupChatList.this, GroupChatRoom.class);
                        //intent.putExtra("userName", contact);
                        intent.putExtra("GroupName", GroupContact);
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



    }
}
