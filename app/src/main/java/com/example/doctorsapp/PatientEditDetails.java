package com.example.doctorsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.doctorsapp.R.id.tvecategory;

public class PatientEditDetails extends AppCompatActivity {
    private TextView tveid,tvename,tveage,tveg,tvephone;
    private Button btnesave;
    private Spinner tvecategory;

    Patdatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_details);
        db=new Patdatabase(this);
        tveid=findViewById(R.id.tveid);
        tvename=findViewById(R.id.tvename);
        tveage=findViewById(R.id.tveage);
        tveg=findViewById(R.id.tveg);
        tvephone=findViewById(R.id.tvephone);
        tvecategory=findViewById(R.id.tvecategory);
        btnesave=findViewById(R.id.btnesave);

        Bundle b=getIntent().getExtras();
        tveid.setText(b.getString("eid"));
        tvename.setText(b.getString("ename"));
        tveg.setText(b.getString("egender"));
        tveage.setText(b.getString("eage"));
        tvephone.setText(b.getString("ephone"));

        tvecategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patients p=new Patients(tveid.getText().toString(),tvename.getText().toString(),tvecategory.getSelectedItem().toString());
                String cate=tvecategory.getSelectedItem().toString();
                String id=tveid.getText().toString();
                db.updatePatientDetails(id,cate);
                Toast.makeText(PatientEditDetails.this, "Details saved ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PatientEditDetails.this,MainActivityFragment.class));
            }
        });

    }
}
