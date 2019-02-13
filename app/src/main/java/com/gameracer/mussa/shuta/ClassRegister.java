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
import android.widget.Toast;

import com.gameracer.mussa.shuta.provider.Classs;
import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ClassRegister extends AppCompatActivity {
    EditText className;
    Button saveClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);

        intiView();
//        DBHelper shuta=new DBHelper(getApplicationContext());
//        SQLiteDatabase db=shuta.getReadableDatabase();

    }
    public void intiView(){
        className=(EditText)findViewById(R.id.className);
        saveClass=(Button)findViewById(R.id.saveClass);

        saveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClassAction(className);
            }
        });
    }
    public void saveClassAction(EditText className){
        String clsnm = className.getText().toString();
        if(clsnm.equals("")){
            Toast.makeText(ClassRegister.this,"class name is empty",Toast.LENGTH_LONG).show();
        }

        else {
            Classs mclass = new Classs(clsnm);
            addClass(mclass);
            Toast.makeText(ClassRegister.this, "thanks the class is registered ", Toast.LENGTH_LONG).show();
            className.setText("");
        }

    }

    public void addClass(Classs mclass) {

        ContentValues values = new ContentValues();
        values.put(ShutaProvider.CLASS_COLUMN_NAME, mclass.getName());
        getContentResolver().insert(ShutaProvider.CLASS_URI , values);
    }
}
