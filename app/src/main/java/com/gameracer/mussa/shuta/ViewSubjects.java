package com.gameracer.mussa.shuta;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ViewSubjects extends AppCompatActivity {
    private ListView subjectListView;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subjects);


        DBHelper shuta=new DBHelper(this);

        subjectListView=(ListView)findViewById(R.id.subjectListView);
//        text=(TextView)findViewById(R.id.result);

//        SQLiteDatabase db = shuta.getWritableDatabase();
        String[] mprojection=new String[]{ShutaProvider.SUBJECT_COLUMN_CODE,ShutaProvider.SUBJECT_COLUMN_NAME};
//        Cursor sub1 = getContentResolver().query(Uri.parse(DBHelper.SUBJECT_TABLE_NAME),mprojection,null,null,null,null);

        //        String sub3= sub1.toString();

//        SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,shuta.getAllSubject(),mprojection,new String[]{android.R.layout.t, 0);
//        SubjectViewAdapter subjectViewAdapter=new SubjectViewAdapter(this ,shuta.getAllSubject(),0);
        CursorAdapter cursorAdapter= new CursorAdapter(this,getAllSubject(),0) {
            private LayoutInflater cursorInflater;

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                cursorInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                return cursorInflater.inflate(R.layout.list_subject_row, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                // Find fields to populate in inflated template
                TextView subcode = (TextView) view.findViewById(R.id.subjectCodeListView);
                TextView subname = (TextView) view.findViewById(R.id.subjectNameListView);
                // Extract properties from cursor
                String subjectcode = cursor.getString(0);
                String subjectname = cursor.getString(1);
                // Populate fields with extracted properties
                subcode.setText(subjectcode);
                subname.setText(subjectname);
            }
        };
        subjectListView.setAdapter(cursorAdapter);
//        text.setText(sub3);
    }
    public Cursor getAllSubject(){
        String[] projection = {ShutaProvider.SUBJECT_COLUMN_CODE, ShutaProvider.SUBJECT_COLUMN_NAME};
        Cursor mcursor=getContentResolver().query(ShutaProvider.SUBJECT_URI,projection,null,null,null);
        return mcursor;
    }
}
