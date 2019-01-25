package com.gameracer.mussa.shuta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.gameracer.mussa.shuta.provider.Classs;
import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ClassRegister extends AppCompatActivity {
    EditText className;
    ListView classListView;
    Button saveClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);

        intiView();
        DBHelper shuta=new DBHelper(getApplicationContext());
        SQLiteDatabase db=shuta.getReadableDatabase();
        String[] mprojection={ShutaProvider.CLASS_COLUMN_CODE,ShutaProvider.CLASS_COLUMN_NAME};
        int[] to={R.id.rowclassid,R.id.rowclassname};
//        Cursor cursor=getContentResolver().query(ShutaProvider.CLASS_URI,mprojection,null,null,null);
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.list_class_row,getAllClass(),mprojection,to,0);
//       +
        classListView.setAdapter(simpleCursorAdapter);
    }
    public void intiView(){
        className=(EditText)findViewById(R.id.className);
        saveClass=(Button)findViewById(R.id.saveClass);
        classListView=(ListView)findViewById(R.id.classListView);

        saveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClassAction(className);
            }
        });
    }
    public void saveClassAction(EditText className){
        String clsnm=className.getText().toString();
        Classs mclass=new Classs(clsnm);
        DBHelper shuta=new DBHelper(getApplicationContext());
        addClass(mclass);
    }
    public Cursor getAllClass(){
        String[] projection = {ShutaProvider.CLASS_COLUMN_CODE, ShutaProvider.CLASS_COLUMN_NAME};
        Cursor mcursor=getContentResolver().query(ShutaProvider.CLASS_URI,projection,null,null,null);
        return mcursor;
    }
    public void addClass(Classs mclass) {

        ContentValues values = new ContentValues();
        values.put(ShutaProvider.CLASS_COLUMN_NAME, mclass.getName());
        getContentResolver().insert(ShutaProvider.CLASS_URI , values);
    }
}
