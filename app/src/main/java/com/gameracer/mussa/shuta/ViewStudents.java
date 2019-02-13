package com.gameracer.mussa.shuta;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ViewStudents extends AppCompatActivity {
    ListView studentListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        studentListView=(ListView)findViewById(R.id.studentListView);
        String[] mprojection = {ShutaProvider.STUDENT_COLUMN_ID,
                ShutaProvider.STUDENT_COLUMN_FNAME,
                ShutaProvider.STUDENT_COLUMN_MNAME,
                ShutaProvider.STUDENT_COLUMN_LNAME,
                ShutaProvider.STUDENT_COLUMN_PASSWORD
                };
        int[] to={R.id.rowregNoListView,R.id.rowstudentFNameListView,R.id.rowstudentMNameListView,R.id.rowstudentLNameListView,R.id.rowstudentPasswordListView};
        SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this,R.layout.list_student_row,getAllStudent(),mprojection,to,0);
        studentListView.setAdapter(simpleCursorAdapter);
    }

    public Cursor getAllStudent(){
        String[] projection = {ShutaProvider.STUDENT_COLUMN_ID,
                ShutaProvider.STUDENT_COLUMN_FNAME,
                ShutaProvider.STUDENT_COLUMN_MNAME,
                ShutaProvider.STUDENT_COLUMN_LNAME,
                ShutaProvider.STUDENT_COLUMN_PASSWORD};
        Cursor mcursor=getContentResolver().query(ShutaProvider.STUDENT_URI,projection,null,null,null);
        return mcursor;
    }
}
