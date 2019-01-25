package com.gameracer.mussa.shuta;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.ShutaProvider;
import com.gameracer.mussa.shuta.provider.Subject;

public class SubjectRegister extends AppCompatActivity {
    private EditText subjectCode, subjectName;
    private Button saveSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_subject);
        initView();

    }

    private void initView(){
        subjectCode=(EditText)findViewById(R.id.subjectCode);
        subjectName=(EditText)findViewById(R.id.subjectName);
        saveSubject=(Button)findViewById(R.id.saveSubject);

        saveSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSubjectAction(subjectCode,subjectName);
            }
        });
    }

    private void saveSubjectAction(EditText subjectCode,EditText subjectName){
        String subCode=subjectCode.getText().toString();
        String subName=subjectName.getText().toString();
        if(subCode.equals("")&& subName.equals("")){
            Toast.makeText(SubjectRegister.this,"the field are empty",Toast.LENGTH_LONG).show();
        }
        if(subCode.equals("")){
            Toast.makeText(SubjectRegister.this,"subject code is empty",Toast.LENGTH_LONG).show();
        }
        if(subName.equals("")){
            Toast.makeText(SubjectRegister.this,"subject name is empty",Toast.LENGTH_LONG).show();
        }
        else {

            Subject subject=new Subject(subCode,subName);
            DBHelper shuta=new DBHelper(this);
            addSubject(subject);
            Toast.makeText(SubjectRegister.this,subCode+" "+subName,Toast.LENGTH_LONG).show();
        }
    }
    public void addSubject(Subject subject) {

        ContentValues values = new ContentValues();
        values.put(ShutaProvider.SUBJECT_COLUMN_CODE, subject.getSubjectCode());
        values.put(ShutaProvider.SUBJECT_COLUMN_NAME, subject.getSubjectName());

        getContentResolver().insert(ShutaProvider.SUBJECT_URI, values);
    }
}
