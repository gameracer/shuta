package com.gameracer.mussa.shuta;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.gameracer.mussa.shuta.provider.ShutaProvider;

public class ViewLocations extends AppCompatActivity {
    ListView locationsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);
        locationsView=(ListView)findViewById(R.id.locationListView);

        String[] mprojection={ShutaProvider.LOCATION_COLUMN_ID,
                ShutaProvider.LOCATION_COLUMN_REGION,
                ShutaProvider.LOCATION_COLUMN_DISTRICT,
                ShutaProvider.LOCATION_COLUMN_WORD};
        int[] to={R.id.rowLocationId,R.id.rowRegion,R.id.rowDistrict,R.id.rowWard};
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.list_location_row,getAllLocation(),mprojection,to,0);
        locationsView.setAdapter(simpleCursorAdapter);

    }
    public Cursor getAllLocation(){
        String[] projection = {ShutaProvider.LOCATION_COLUMN_ID,
                ShutaProvider.LOCATION_COLUMN_REGION,
                ShutaProvider.LOCATION_COLUMN_DISTRICT,
                ShutaProvider.LOCATION_COLUMN_WORD};
        Cursor mcursor=getContentResolver().query(ShutaProvider.LOCATION_URI,projection,null,null,null);
        return mcursor;
    }
}
