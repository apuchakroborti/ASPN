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

public class FragmentChat3 extends Fragment {


    private String userName;//current userName
    private String choosenContact;//the contact that i send messages

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send1);

    //01711302074

    }*/

    ListView listView;
    String items[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        //
        //List<Login> listEmails = JsonUtil.getAllEmails(json);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(listEmails.size());
        for (Login loginObj : listEmails) {

            HashMap<String, String> item = new HashMap<String, String>();
            item.put("email", loginObj.getEmailAndress());
            item.put("institution", loginObj.getInstitution());

            list.add(item);
        }
        String[] from = new String[] { "email", "institution" };
        int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
        int nativeLayout = android.R.layout.two_line_list_item;
        emailListView.setAdapter(new SimpleAdapter(this, list, nativeLayout , from, to));
        //*/


        View rootView = inflater.inflate(R.layout.activity_send_messages, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.tvFragmentChat);
        //textView.setText("This is Fragment1");


        //View rootView =inflater.inflate(R.class.getClass(startActivity()));

        final ListView listView = (ListView) rootView.findViewById(R.id.lvAdd);

        //final TextView item1=(TextView)rootView.findViewById(R.id.tvItem);
        //final TextView item2=(TextView)rootView.findViewById(R.id.tvSubItem);

        //items = getActivity().getResources().getStringArray(R.array.test);
        //lvHo = (ListView) getActivity().findViewById(R.id.lvHomePage);

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_2 , android.R.id.text1);
       // HashMap<String, String> item = new HashMap<String, String>();
        //final ArrayAdapter<HashMap<String,String>> adapter=new ArrayAdapter<HashMap<String, String>>(getActivity()
          //      .getApplicationContext(),
            //    android.R.layout.simple_list_item_2 , android.R.id.text1);

       listView.setAdapter(adapter);
        //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        //listView.setAdapter(adapter);

        // Add items via the Button and EditText at the bottom of the view.
        final EditText text = (EditText) rootView.findViewById(R.id.etGetText);
        final Button button = (Button) rootView.findViewById(R.id.bAdd);
        final String email="";

        //

/*
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();


        HashMap<String, String> item = new HashMap<String, String>();
        item.put("messages", text.getText().toString());
        item.put("name", "userName");



        String[] from = new String[] { "messages", "name" };
        int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
        int nativeLayout = android.R.layout.simple_list_item_2;
        listView.setAdapter(new ArrayAdapter<>(this,list,nativeLayout,from,to));
  */
        //


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //item1.setText(text.getText().toString());
                //item2.setText("apu");

                //adapter.add(item1.getText().toString()+"\n\b"+item2.getText().toString());
                //adapter.add("apu");
                //list.add(item);
                adapter.add(text.getText().toString()+"\n\b"+"apu");
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
