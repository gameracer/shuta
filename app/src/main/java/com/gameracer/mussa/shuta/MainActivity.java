package com.gameracer.mussa.shuta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class MainActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;

    //session handler
    public static final String MyPREFERENCES = "shutaPrefs" ;
    public static final String RegNo = "RegNoKey";
    public static final String Password = "PasswordKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
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

    public void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAction(username.getText().toString(), password.getText().toString());
            }
        });
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }


    public void loginAction(String uname, String pwd) {
        Cursor dataSudent = getAllStudent();
        //admin
        if (uname.equals("admin") && pwd.equals("admin")) {
            Toast.makeText(getApplicationContext(),
                    "Redirecting...", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(MainActivity.this, Admin.class);
            startActivity(a);

        } else if (dataSudent != null) {
            /*
             * Moves to the next row in the cursor. Before the first movement in the cursor, the
             * "row pointer" is -1, and if you try to retrieve data at that position you will get an
             * exception.
             */
            while (dataSudent.moveToNext()) {
                // student
                if (uname.equals(dataSudent.getString(0)) && pwd.equals(dataSudent.getString(1))) {
                    //save preshared key
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(RegNo, uname);
                    editor.putString(Password, pwd);
                    editor.commit();

                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent studentMainActivity=new Intent(this,StudentMainActivity.class);
                    startActivity(studentMainActivity);
                }
                // Gets the value from the column.
//                newWord = mCursor.getString(index);
                // Insert code here to process the retrieved word.
                // end of while loop
            }
//         else {
//
//                // Insert code here to report an error if the cursor is null or the provider threw an exception.
//            }
        }

        //teacher
//        else if(){
//
//        }
        else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }

        //  Toast.makeText(this,"master tony",Toast.LENGTH_SHORT).show();
    }



    public Cursor getAllStudent(){
        String[] projection = {ShutaProvider.STUDENT_COLUMN_ID,
                ShutaProvider.STUDENT_COLUMN_PASSWORD,
                ShutaProvider.STUDENT_COLUMN_MNAME,
                ShutaProvider.STUDENT_COLUMN_LNAME};
        Cursor mcursor=getContentResolver().query(ShutaProvider.STUDENT_URI,projection,null,null,null);
        return mcursor;
    }




}
