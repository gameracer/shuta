package com.gameracer.mussa.shuta.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.gameracer.mussa.shuta.provider.DBHelper;


public class ShutaProvider extends ContentProvider {

    private static final String TABLE_SUBJECT= "table_subject";
    private static final String TABLE_TEACHER= "table_teacher";
    private static final String TABLE_STUDENT= "table_location";
    private static final String TABLE_LOCATION= "table_location";
    private static final String TABLE_CLASS= "table_class";
    private static final String TABLE_ACADEMIC= "table_academic";


    static final String AUTHORITY = "com.gameracer.mussa.shuta.provider.ShutaProvider";
    public static final Uri SUBJECT_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_SUBJECT);
    public static final Uri TEACHER_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_TEACHER);
    public static final Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_STUDENT);
    public static final Uri LOCATION_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_LOCATION);
    public static final Uri ACADEMIC_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_ACADEMIC);
    public static final Uri CLASS_URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_CLASS);

    public static final int SUBJECT=1;
    public static final int SUBJECT_ID=2;
    public static final int TEACHER=3;
    public static final int TEACHER_ID=4;
    public static final int STUDENT=5;
    public static final int STUDENT_ID=6;
    public static final int LOCATION=7;
    public static final int LOCATION_ID=8;
    public static final int ACADEMIC=9;
    public static final int ACADEMIC_ID=10;
    public static final int CLASS=11;
    public static final int CLASS_ID=12;


    private static final UriMatcher sURIMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    DBHelper myshuta;
    static {
        sURIMatcher.addURI(AUTHORITY, TABLE_SUBJECT, SUBJECT);
        sURIMatcher.addURI(AUTHORITY, TABLE_SUBJECT + "/#",SUBJECT_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE_TEACHER, TEACHER );
        sURIMatcher.addURI(AUTHORITY, TABLE_TEACHER + "/#",TEACHER_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE_STUDENT, STUDENT );
        sURIMatcher.addURI(AUTHORITY, TABLE_STUDENT + "/#",STUDENT_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE_TEACHER, LOCATION );
        sURIMatcher.addURI(AUTHORITY, TABLE_LOCATION + "/#",LOCATION_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE_ACADEMIC, ACADEMIC );
        sURIMatcher.addURI(AUTHORITY, TABLE_ACADEMIC + "/#",ACADEMIC_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE_CLASS, CLASS );
        sURIMatcher.addURI(AUTHORITY, TABLE_CLASS + "/#",CLASS_ID);
    }

    @Override
    public boolean onCreate() {
         myshuta = new DBHelper(getContext());
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(DBHelper.SUBJECT_TABLE_NAME);

            int uriType = sURIMatcher.match(uri);
//        SQLiteDatabase db = myshuta.getWritableDatabase();

            switch (uriType) {
                case SUBJECT_ID:
                    queryBuilder.appendWhere(DBHelper.SUBJECT_COLUMN_CODE + "="
                            + uri.getLastPathSegment());
                    break;
                case SUBJECT:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI");
            }

            Cursor cursor = queryBuilder.query(myshuta.getReadableDatabase(),
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(),
                    uri);
            return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //decleration
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase db = myshuta.getWritableDatabase();
        long id = 0;
        //Use the sUriMatcher to identify the URI type.
        switch (uriType) {
            case SUBJECT:
                id = db.insert(DBHelper.SUBJECT_TABLE_NAME,null, values);
                break;
            case  TEACHER:
                id = db.insert(DBHelper.TEACHER_TABLE_NAME,null, values);
                break;
            case  STUDENT:
                id = db.insert(DBHelper.STUDENT_TABLE_NAME,null, values);
                break;
            case  LOCATION:
                id = db.insert(DBHelper.LOCATION_TABLE_NAME,null, values);
                break;
            case  ACADEMIC:
                id = db.insert(DBHelper.ACADEMICSESION_TABLE_NAME,null, values);
                break;
            case  CLASS:
                id = db.insert(DBHelper.CLASS_TABLE_NAME,null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(TABLE_SUBJECT + "/" + id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myshuta.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case SUBJECT:
                rowsDeleted = sqlDB.delete(DBHelper.SUBJECT_TABLE_NAME, selection, selectionArgs);
                break;

            case SUBJECT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(DBHelper.SUBJECT_TABLE_NAME,
                            DBHelper.SUBJECT_COLUMN_CODE + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(DBHelper.SUBJECT_TABLE_NAME,
                            DBHelper.SUBJECT_COLUMN_CODE + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myshuta.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case SUBJECT:
                rowsUpdated =
                        sqlDB.update(DBHelper.SUBJECT_TABLE_NAME,
                                values,
                                selection,
                                selectionArgs);
                break;
            case SUBJECT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(DBHelper.SUBJECT_TABLE_NAME, values, DBHelper.SUBJECT_COLUMN_CODE + "=" + id, null);
                } else {
                    rowsUpdated =
                            sqlDB.update(DBHelper.SUBJECT_TABLE_NAME, values,DBHelper.SUBJECT_COLUMN_CODE + "=" + id + " and " + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " +
                        uri);
        }
        getContext().getContentResolver().notifyChange(uri,
                null);
        return rowsUpdated;

    }


}
