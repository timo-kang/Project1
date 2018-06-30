package com.example.q.project1;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReadContact extends Fragment {

    public ReadContact(){

    }
    ListView listView = null;
//    ImageButton contacts = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.readcontact, container, false);
//        contacts = (ImageButton)view.findViewById(R.id.contacts);
        listView = (ListView)view.findViewById(R.id.result);

        //inserting button here

//        contacts.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v){

                //to do

                ContentResolver cr = getActivity().getContentResolver();
                Cursor cursor = cr.query(
                        ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

                int ididx = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

                StringBuilder result = new StringBuilder();
                while(cursor.moveToNext())
                {
                    result.append(cursor.getString(nameidx) + " :");

                    String id = cursor.getString(ididx);
                    Cursor cursor2 = cr.query(ContactsContract.CommonDataKinds.
                                    Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                            new String[]{id},null);

                    int typeidx = cursor2.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.TYPE);

                    int numidx = cursor2.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER);

                    while (cursor2.moveToNext()){
                        String num = cursor2.getString(numidx);
                        switch(cursor2.getInt(typeidx)){
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                result.append("\t Mobile:"+num);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                result.append("\t Home:"+num);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                result.append("\t Work:"+num);
                                break;
                        }
                    }
                    cursor2.close();
                    result.append("\n");

                }
                cursor.close();

                //inflate was here
                String str= result.toString();
                ArrayList<String>arr_list = new ArrayList<String>();

                String[] str1=str.split("\n");
                for(int i=0;i<str1.length;i++){

                    arr_list.add(str1[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1,  arr_list);
                // Assign adapter to ListView
                adapter.sort(new Comparator<String>(){

                    @Override
                    public int compare(String arg1,String arg0){
                        return arg1.compareTo(arg0);
                    }
                });
                listView.setAdapter(adapter);


//            }
//
//        });//ends here



        return view;
    }

}
