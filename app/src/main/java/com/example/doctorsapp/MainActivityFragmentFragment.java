package com.example.doctorsapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentFragment extends Fragment {
    private ArrayList<Patients> clist = new ArrayList<>();
    PatientAdapter pa;
    private Patdatabase db;
    private ListView lvpatients;
    private int selectedPos;



    public MainActivityFragmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new Patdatabase(getActivity());
         clist = db.getAllPatients();
        View root = inflater.inflate(R.layout.fragment_main_activity_fragment, container, false);
        //clist.add(new Patients("01", "Prabhas", "Emergency"));
        //clist.add(new Patients("02", "Antick", "NORMAL"));
        pa = new PatientAdapter(getActivity(), clist);
        lvpatients=root.findViewById(R.id.lvpatients);
        lvpatients.setAdapter(pa);
        TextView etpatid=(TextView) root.findViewById(R.id.etpatid);
        TextView tvpatName=(TextView)root.findViewById(R.id.tvpatname);
        TextView tvpatctgr=(TextView)root.findViewById(R.id.tvpatctgr);
        registerForContextMenu(lvpatients);
        return root;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo menuInfo1=(AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedPos=menuInfo1.position;
        getActivity().getMenuInflater().inflate(R.menu.pat_ctx_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnugd:
                Intent i=new Intent(getActivity(),PatientGetDetails.class);
                Bundle b=new Bundle();
                String id=clist.get(selectedPos).getPatientID().toString();
                String nm=clist.get(selectedPos).getPatientName().toString();
                String cat=clist.get(selectedPos).getPatientCategory().toString();
                String age="27";
                String gender="M";
                String phone="123456789";
                b.putString("id",id);
                b.putString("name",nm);
                b.putString("category",cat);
                b.putString("gender",gender);
                b.putString("age",age);
                b.putString("phone",phone);
                i.putExtras(b);
                startActivity(i);
                //Toast.makeText(getActivity(), "Display Patient Details: "+clist.get(selectedPos), Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnued:
                Intent ie=new Intent(getActivity(),PatientEditDetails.class);
                Bundle be=new Bundle();
                String eid=clist.get(selectedPos).getPatientID().toString();
                String enm=clist.get(selectedPos).getPatientName().toString();
                String eage="27";
                String egender="M";
                String ephone="123456789";
                be.putString("eid",eid);
                be.putString("ename",enm);
                be.putString("egender",egender);
                be.putString("eage",eage);
                be.putString("ephone",ephone);
                ie.putExtras(be);
                startActivity(ie);
                //Toast.makeText(getActivity(), "Edit Patient Details: "+clist.get(selectedPos), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}