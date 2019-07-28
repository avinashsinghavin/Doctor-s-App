package com.example.doctorsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class PatientDetailsFragment extends Fragment {
    private EditText editText;
    private TextView tvname;
    private TextView tvage;
    private TextView tvg;
    private TextView tvp;
    private Spinner spncategory;
    private Button btncall, btnmsg, btnsave;

    Patdatabase db;

    public PatientDetailsFragment() {
    }
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: called ....#########");
            Bundle b=intent.getExtras();
            Object obj[]=(Object[])b.get("pdus");
            SmsMessage msgs[]=new SmsMessage[obj.length];

            for(int i=0;i<msgs.length;i++){
                msgs[i]=SmsMessage.createFromPdu((byte [])obj[i]);
            }
            for(SmsMessage msg : msgs){
                String addr=msg.getOriginatingAddress();
                String body=msg.getMessageBody();
                String parts[]=body.split(",");
                tvname.setText(parts[0]);
                tvage.setText(parts[1]);
                tvg.setText(parts[2]);
                tvp.setText(parts[3]);
                Log.i("INRECEIVER", "###### SMS RECEIVED : ADDR : "+addr+" , MESSAGE : "+body);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver,new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db=new Patdatabase(getActivity());
        View root= inflater.inflate(R.layout.fragment_patient_details, container, false);
        editText=root.findViewById(R.id.editText);
        tvname=root.findViewById(R.id.tvname);
        tvage=root.findViewById(R.id.tvage);
        tvg=root.findViewById(R.id.tvg);
        tvp=root.findViewById(R.id.tvp);
        spncategory=root.findViewById(R.id.spncategory);
        btnsave=root.findViewById(R.id.btnsave);
        spncategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btncall=root.findViewById(R.id.btncall);
        btnmsg=root.findViewById(R.id.btnmsg);
        btnsave=root.findViewById(R.id.btnsave);
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=tvp.getText().toString();
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel://"+tel));
                startActivity(i);
            }
        });
        btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=tvp.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:"+tel));
                startActivity(intent);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patients p=new Patients(editText.getText().toString(),tvname.getText().toString(),spncategory.getSelectedItem().toString());
                db.createPatient(p);
                Toast.makeText(getActivity(), "Details saved ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),MainActivityFragment.class));
            }
        });
        return root;
    }
}
