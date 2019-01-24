package com.gameracer.mussa.shuta;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gameracer.mussa.shuta.provider.DBHelper;

public class SubjectViewAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    public SubjectViewAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.list_subject, parent, false);
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
}
