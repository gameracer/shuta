package com.gameracer.mussa.shuta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    private Button addAcademicSession,addLocation,addClass, addStudent, addTeacher, addSubject, viewStudents,
            viewTeachers, viewSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
    }
    public void initView(){
    addAcademicSession=(Button)findViewById(R.id.addAcademicSession);
    addLocation=(Button)findViewById(R.id.addLocation);
    addStudent=(Button)findViewById(R.id.addStudent);
    addTeacher=(Button)findViewById(R.id.addTeacher);
    addSubject=(Button)findViewById(R.id.addSubject);
    addClass=(Button)findViewById(R.id.addClass);
    viewStudents=(Button)findViewById(R.id.viewStudents);
    viewSubjects=(Button)findViewById(R.id.viewSubjects);
    viewTeachers=(Button)findViewById(R.id.viewTeachers);

        addAcademicSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent academicRegiser= new Intent(Admin.this, AcademicRegister.class);
             startActivity(academicRegiser);
            }
        });
        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationRegister= new Intent(Admin.this, LocationRegister.class);
                startActivity(locationRegister);
            }
        });
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationRegister= new Intent(Admin.this, ClassRegister.class);
                startActivity(locationRegister);
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentRegister=new Intent(Admin.this, StudentRegister.class);
                startActivity(studentRegister);
            }
        });
        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teacherRegister=new Intent(Admin.this, TeacherRegister.class);
                startActivity(teacherRegister);
            }
        });
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectRegister=new Intent(Admin.this, SubjectRegister.class);
                startActivity(subjectRegister);
            }
        });
        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewStudents= new Intent(Admin.this, ViewStudents.class);
                startActivity(viewStudents);
            }
        });
        viewTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewTeachers= new Intent(Admin.this, ViewTeachers.class);
                startActivity(viewTeachers);
            }
        });
        viewSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSubjects= new Intent(Admin.this, ViewSubjects.class);
                startActivity(viewSubjects);
            }
        });

    }

}


