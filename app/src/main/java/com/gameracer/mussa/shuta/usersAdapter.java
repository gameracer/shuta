package com.gameracer.mussa.shuta;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class usersAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    public usersAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(
               Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.myview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView id = (TextView) view.findViewById(R.id.vId);
        TextView name = (TextView) view.findViewById(R.id.vname);
        TextView role = (TextView) view.findViewById(R.id.vrole);
        // Extract properties from cursor
        String cName = cursor.getString(1);
        int cID = cursor.getInt(0);
        String cRole = cursor.getString(2);
        // Populate fields with extracted properties
        name.setText(cName);
        role.setText(cRole);
        id.setText(String.valueOf(cID));
    }
}
