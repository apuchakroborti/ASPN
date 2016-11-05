package com.example.apu.safechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

/**
 * Created by apuchakroborti on 10/31/2016.
 */

public class CreateGroupChat extends Activity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private String userName;//current userName



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_chat);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Add items via the Button and EditText at the bottom of the view.
        final EditText text = (EditText) findViewById(R.id.etGetGroupName);
        final Button button = (Button) findViewById(R.id.bCreateGroup);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mDatabase.child("users").child("GroupNames").push().child("name").setValue(text.getText().toString());
                //mDatabase.child("users").child("GroupNames").push().child("name").setValue(text.getText().toString());
                String gn=text.getText().toString();
                if(gn.length()<=0){
                    gn=" ";
                }

                text.setText("");
                mDatabase.child("users").child("GroupNames1").push().child("name").setValue(gn);

                mDatabase.child("users").child("GroupNames").child(gn).child("messages").push().child("title").setValue(" ");
                //mDatabase.child("users").child("GroupNames").child(gn);

                //Intent intent=new Intent(CreateGroupChat.this,GroupChatRoom.class);
                Intent intent=new Intent(CreateGroupChat.this,GroupChatList.class);
                //intent.putExtra("GroupName",gn);
                startActivity(intent);
            }
        });



    }
}
