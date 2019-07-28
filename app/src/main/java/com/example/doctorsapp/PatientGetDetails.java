package com.example.doctorsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PatientGetDetails extends AppCompatActivity {
    private TextView tvgid,tvgname,tvgage,tvgg,tvgphone,tvgcategory;
    private Button btngcall,btngmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_get_details);
        tvgid=findViewById(R.id.tvgid);
        tvgname=findViewById(R.id.tvgname);
        tvgage=findViewById(R.id.tvgage);
        tvgg=findViewById(R.id.tvgg);
        tvgphone=findViewById(R.id.tvgphone);
        tvgcategory=findViewById(R.id.tvgcategory);
        btngcall=findViewById(R.id.btngcall);
        btngmsg=findViewById(R.id.btngmsg);

        Bundle b=getIntent().getExtras();
        tvgid.setText(b.getString("id"));
        tvgname.setText(b.getString("name"));
        tvgg.setText(b.getString("gender"));
        tvgage.setText(b.getString("age"));
        tvgphone.setText(b.getString("phone"));
        tvgcategory.setText(b.getString("category"));

        btngcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=tvgphone.getText().toString();
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+tel));
                startActivity(i);
            }
        });
        btngmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=tvgphone.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:"+tel));
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
