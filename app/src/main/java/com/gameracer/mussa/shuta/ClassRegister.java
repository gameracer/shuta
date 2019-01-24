package com.gameracer.mussa.shuta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClassRegister extends AppCompatActivity {
    EditText className;
    Button saveClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);

        intiView();

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

    }
}
