package com.gameracer.mussa.shuta;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameracer.mussa.shuta.provider.DBHelper;
import com.gameracer.mussa.shuta.provider.Location;
import com.gameracer.mussa.shuta.provider.ShutaProvider;

import java.util.Random;

public class LocationRegister extends AppCompatActivity {
    EditText region,ward,district;
    Button saveLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_location);
        initVeiw();
    }
    public void initVeiw(){
        region=(EditText)findViewById(R.id.region);
        ward=(EditText)findViewById(R.id.ward);
        district=(EditText)findViewById(R.id.district);
        saveLocation=(Button) findViewById(R.id.saveLocation);

        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocationAction(region,ward,district);
            }
        });

    }


    public void saveLocationAction(EditText region,EditText ward,EditText District){
        Random number=new Random();
        int id = number.nextInt(5000)+2018;
        String rg=region.getText().toString();
        String dsc=District.getText().toString();
        String wrd=ward.getText().toString();

        if(rg.equals("")&& dsc.equals("")&& wrd.equals("")){
            Toast.makeText(this,"the field are empty",Toast.LENGTH_LONG).show();
        }
        if(rg.equals("")){
            Toast.makeText(this,"region is empty",Toast.LENGTH_LONG).show();
        }
        if(dsc.equals("")){
            Toast.makeText(this,"district name is empty",Toast.LENGTH_LONG).show();
        }
        if(wrd.equals("")){
            Toast.makeText(this,"ward name is empty",Toast.LENGTH_LONG).show();
        }
        else {

            Location location = new Location(id, rg, dsc, wrd);
            DBHelper shuta = new DBHelper(getApplicationContext());
            addLocation(location);
        }

    }
    public void addLocation(Location location) {
        ContentValues values = new ContentValues();
        values.put(ShutaProvider.LOCATION_COLUMN_ID,location.getID());
        values.put(ShutaProvider.LOCATION_COLUMN_REGION, location.getRegion());
        values.put(ShutaProvider.LOCATION_COLUMN_DISTRICT, location.getDistrict());
        values.put(ShutaProvider.LOCATION_COLUMN_WORD, location.getWord());
        getContentResolver().insert(ShutaProvider.LOCATION_URI, values);
    }
}
