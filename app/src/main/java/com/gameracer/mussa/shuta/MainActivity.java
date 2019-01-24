package com.gameracer.mussa.shuta;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameracer.mussa.shuta.provider.DBHelper;
//import android.database.sqlite;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    public EditText username;
    public EditText password;
    public Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
        DBHelper shuta=new DBHelper(this);

        SQLiteDatabase db = shuta.getWritableDatabase();
        initView();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.item1) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void initView(){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginAction(username.getText().toString(),password.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, register_student.class);
                startActivity(i);
            }
        });

       
    }



    public void loginAction(String uname, String pwd) {

        if(uname.equals("admin") && pwd.equals("admin")){
            Toast.makeText(getApplicationContext(),
                  "Redirecting...",Toast.LENGTH_SHORT).show();
            Intent a=new Intent(MainActivity.this, Admin.class);
            startActivity(a);

        }else{

            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
        }

      //  Toast.makeText(this,"master tony",Toast.LENGTH_SHORT).show();
    }






}
