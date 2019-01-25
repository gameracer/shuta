package com.gameracer.mussa.shuta;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gameracer.mussa.shuta.provider.ShutaProvider;
import com.gameracer.mussa.shuta.provider.Teacher;

public class TeacherRegister extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
    }

    public void addTeacher(Teacher teacher) {

        ContentValues values = new ContentValues();
        values.put(ShutaProvider.TEACHER_COLUMN_ID, teacher.getId() );
        values.put(ShutaProvider.TEACHER_COLUMN_FNAME,teacher.getFname() );
        values.put(ShutaProvider.TEACHER_COLUMN_MNAME,teacher.getMname() );
        values.put(ShutaProvider.TEACHER_COLUMN_LNAME,teacher.getLname() );
        values.put(ShutaProvider.TEACHER_COLUMN_PASSWORD, teacher.getPassword());
        values.put(ShutaProvider.TEACHER_COLUMN_GENDER, teacher.getGender());
        values.put(ShutaProvider.TEACHER_COLUMN_EMAIL,teacher.getEmail() );
        values.put(ShutaProvider.TEACHER_COLUMN_PHONENO, teacher.getPhoneNo());
        values.put(ShutaProvider.TEACHER_COLUMN_LOCATION, teacher.getLocationID() );
        getContentResolver().insert(ShutaProvider.SUBJECT_URI, values);
    }
}
