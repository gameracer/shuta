package com.gameracer.mussa.shuta;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ViewClasses extends AppCompatActivity {
    ListView classListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);

        classListView=(ListView)findViewById(R.id.classListView);

        String[] mprojection={ShutaProvider.CLASS_COLUMN_CODE,ShutaProvider.CLASS_COLUMN_NAME};
        int[] to={R.id.rowclassid,R.id.rowclassname};
//        Cursor cursor=getContentResolver().query(ShutaProvider.CLASS_URI,mprojection,null,null,null);
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.list_class_row,getAllClass(),mprojection,to,0);
//       +
        classListView.setAdapter(simpleCursorAdapter);
    }

    public Cursor getAllClass(){
        String[] projection = {ShutaProvider.CLASS_COLUMN_CODE, ShutaProvider.CLASS_COLUMN_NAME};
        Cursor mcursor=getContentResolver().query(ShutaProvider.CLASS_URI,projection,null,null,null);
        return mcursor;
    }
}
