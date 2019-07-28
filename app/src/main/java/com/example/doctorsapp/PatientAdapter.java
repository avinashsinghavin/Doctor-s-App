package com.example.doctorsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patients> {
    private Context context;
    List<Patients> clist;

    public PatientAdapter(Context context, List<Patients> list) {
        super(context, R.layout.layout_patients_lv, list);
        this.context=context;
        clist=list;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.layout_patients_lv, parent, false);
        TextView etpatid = root.findViewById(R.id.etpatid);
        TextView tvpatname = root.findViewById(R.id.tvpatname);
        TextView tvpatctgr = root.findViewById(R.id.tvpatctgr);
        etpatid.setText(clist.get(position).getPatientID());
        tvpatname.setText(clist.get(position).getPatientName());
        tvpatctgr.setText(clist.get(position).getPatientCategory());
        return root;
    }
}
