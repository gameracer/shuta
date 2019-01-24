package com.gameracer.mussa.shuta;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.gameracer.mussa.shuta.provider.DBHelper;


public class users_management extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;
    int id_To_Update = 0;
    TextView name ;
    TextView role;
    ListView listv;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_management);

//        GridView gridView=(GridView)findViewById(R.id.gridview);
        listv = (ListView) findViewById(R.id.list);
        mydb = new DBHelper(this);


        SQLiteDatabase db;
        db = mydb.getWritableDatabase();
//        mydb.onUpgrade(db,1,2);
        Cursor rs=db.rawQuery("SELECT  * FROM  Users", null);
        usersAdapter usersAdapter= new usersAdapter(this, rs,0);
        listv.setAdapter(usersAdapter);
//        gridView.setAdapter(usersAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {
//            Intent regUser = new Intent(this, register_users.class);
//            startActivity(regUser);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
