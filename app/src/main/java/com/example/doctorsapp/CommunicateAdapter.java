package com.example.doctorsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Currency;
import java.util.List;

public class CommunicateAdapter extends ArrayAdapter<Communicate> {

    private Context context;
    List<Communicate> clist;


    public CommunicateAdapter(Context context,List<Communicate> list) {
        super(context, R.layout.communicate_lv, list);
        this.context=context;
        clist=list;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.communicate_lv,parent,false);
        TextView tvaddr=row.findViewById(R.id.tvaddr);
        TextView tvmsg=row.findViewById(R.id.tvmsg);
        tvaddr.setText(clist.get(position).getAddress());
        tvmsg.setText(clist.get(position).getMessage());
        return row;
    }
}

