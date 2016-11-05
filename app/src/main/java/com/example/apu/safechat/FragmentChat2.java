package com.example.apu.safechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by apuchakroborti on 10/29/2016.
 */

public class FragmentChat2 extends Fragment {


    private String userName;//current userName
    private String choosenContact;//the contact that i send messages


    ListView listView;
    String items[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_send1, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.tvFragmentChat);
        textView.setText("This is Fragment2");


        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        //View rootView =inflater.inflate(R.class.getClass(startActivity()));

        final ListView listView = (ListView) rootView.findViewById(R.id.lvAdd);

        //items = getActivity().getResources().getStringArray(R.array.test);
        //lvHo = (ListView) getActivity().findViewById(R.id.lvHomePage);

        //items = rootView.getResources().getStringArray(R.);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1 , android.R.id.text1);
        listView.setAdapter(adapter);


        //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        //listView.setAdapter(adapter);

        // Add items via the Button and EditText at the bottom of the view.
        final EditText text = (EditText) rootView.findViewById(R.id.etGetText);
        final Button button = (Button) rootView.findViewById(R.id.bAdd);
        final String email="";


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.add(text.getText().toString());
            }
        });


        return rootView;
        //return TabActivity1;
    }

    public String commonString(String email1,String email2)
    {

        String str = email1+email2;

        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }

    public String username(String Email) {
        final String TAG = "SignUpActivity";
        //MainActivity
        //String email = ContactActivity.mAuth.getCurrentUser().getEmail();
        Log.i(TAG, "usernameOfCurrentUser(): " + Email);
        if (Email.contains("@")) {
            return Email.split("@")[0];
        } else {
            return Email;
        }
    }
}
