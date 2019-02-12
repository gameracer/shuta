package com.gameracer.mussa.shuta;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ViewAcademics extends AppCompatActivity {
    ListView academicListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_academics);

        academicListView=(ListView)findViewById(R.id.academicListView);

        String[] mprojection={ShutaProvider.ACADEMIC_COLUMN_CODE,
                ShutaProvider.ACADEMIC_COLUMN_NAME,
                ShutaProvider.ACADEMIC_COLUMN_YEAR};
        int[] to={R.id.rowAcademicCode,R.id.rowAcademicName,R.id.rowAcademicYear};
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.list_academic_row,getAllAcademics(),mprojection,to,0);
        academicListView.setAdapter(simpleCursorAdapter);
    }

    public Cursor getAllAcademics(){
        String[] projection = {ShutaProvider.ACADEMIC_COLUMN_CODE,
                ShutaProvider.ACADEMIC_COLUMN_NAME,
                ShutaProvider.ACADEMIC_COLUMN_YEAR};
        Cursor mcursor=getContentResolver().query(ShutaProvider.ACADEMIC_URI,projection,null,null,null);
        return mcursor;
    }
}
