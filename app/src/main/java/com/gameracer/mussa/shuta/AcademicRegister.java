package com.gameracer.mussa.shuta;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameracer.mussa.shuta.provider.Academic;
import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class AcademicRegister extends AppCompatActivity {
    EditText name,year;
    Button saveAcademic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_academic);
        initView();
    }
    public void initView(){
        name=(EditText)findViewById(R.id.academicName);
        year=(EditText)findViewById(R.id.academicYear);
        saveAcademic=(Button)findViewById(R.id.academicSave);

        saveAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAcademicAction(name,year);
            }
        });
    }

    public void saveAcademicAction(EditText name,EditText year){
       String id;
       String nm=name.getText().toString();
       String yr=year.getText().toString();
       id=nm.concat(yr);
        if(nm.equals("")&& yr.equals("")){
            Toast.makeText(this,"the field are empty",Toast.LENGTH_LONG).show();
        }
        if(nm.equals("")){
            Toast.makeText(this,"Academic name is empty",Toast.LENGTH_LONG).show();
        }
        if(yr.equals("")){
            Toast.makeText(this,"academic year is empty",Toast.LENGTH_LONG).show();
        }
        Academic academic= new Academic(id,yr,nm);
        DBHelper shuta= new DBHelper(getApplicationContext());
        addAcademic(academic);
    }
    public void addAcademic(Academic academic) {

        ContentValues values = new ContentValues();
        values.put(ShutaProvider.ACADEMIC_COLUMN_CODE, academic.getAcademicID());
        values.put(ShutaProvider.ACADEMIC_COLUMN_NAME, academic.getName());
        values.put(ShutaProvider.ACADEMIC_COLUMN_YEAR, academic.getYear());

        getContentResolver().insert(ShutaProvider.ACADEMIC_URI, values);
    }
}
