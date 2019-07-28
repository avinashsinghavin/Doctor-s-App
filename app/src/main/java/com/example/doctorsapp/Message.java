package com.example.doctorsapp;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Message extends BroadcastReceiver {
    private String Channel_id="personal_notification";
    private final int Notification_id=001;
    private CommunicateDatabase cd;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: called ....#########");
        cd = new CommunicateDatabase(context);
        Bundle b=intent.getExtras();
        Object obj[]=(Object[])b.get("pdus");
        SmsMessage msgs[]=new SmsMessage[obj.length];

        for(int i=0;i<msgs.length;i++){
            msgs[i]=SmsMessage.createFromPdu((byte [])obj[i]);
        }
        for(SmsMessage msg : msgs){
            String addr=msg.getOriginatingAddress();
            String body=msg.getMessageBody();
            String parts[]=body.split("-");
            if(parts[0].equals("DOC99"))
            {
                String mesg=parts[1];
                Communicate c=new Communicate(addr,mesg);
                cd.createCommunicate(c);
                //Toast.makeText(this, "Message saved ", Toast.LENGTH_SHORT).show();
            }
            Log.i("INRECEIVER", "###### SMS RECEIVED : ADDR : "+addr+" , MESSAGE : "+body);
        }
    }
}