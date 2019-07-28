package com.example.doctorsapp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class CommunicateActivityFragment extends Fragment {

    private ListView lvnotify;
    private ArrayList<Communicate> clist=new ArrayList<>();
    CommunicateAdapter ca;
    private int selectedPos;

    private CommunicateDatabase db;


    public CommunicateActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_communicate, container, false);
        db=new CommunicateDatabase(getActivity());
        clist=db.getAllCommunicates();
        lvnotify=root.findViewById(R.id.lvnotify);
        ca=new CommunicateAdapter(getActivity(),clist);
        lvnotify.setAdapter(ca);

        return root;
    }
}
