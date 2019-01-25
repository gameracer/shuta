package com.gameracer.mussa.shuta.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
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

     //table names
    private static final String TABLE_SUBJECT= "table_subject";
    private static final String TABLE_TEACHER= "table_teacher";
    private static final String TABLE_STUDENT= "table_location";
    private static final String TABLE_LOCATION= "table_location";
    private static final String TABLE_CLASS= "table_class";
    private static final String TABLE_ACADEMIC= "table_academic";

    //table 1
    public static final String TEACHER_TABLE_NAME = "table_teacher";
    public static final String TEACHER_COLUMN_ID = "_id";
    public static final String TEACHER_COLUMN_FNAME = "teacher_fname";
    public static final String TEACHER_COLUMN_MNAME = "teacher_mname";
    public static final String TEACHER_COLUMN_LNAME = "teacher_lname";
    public static final String TEACHER_COLUMN_PASSWORD = "teacher_password";
    public static final String TEACHER_COLUMN_GENDER = "teacher_gender";
    public static final String TEACHER_COLUMN_PHONENO = "teacher_phone";
    public static final String TEACHER_COLUMN_LOCATION = "teacher_location";
    public static final String TEACHER_COLUMN_EMAIL="teacher_Email";

    //table 2 :pg=parent or garadian
    public static final String STUDENT_TABLE_NAME = "table_student";
    public static final String STUDENT_COLUMN_ID = "_id";
    public static final String STUDENT_COLUMN_FNAME = "student_fname";
    public static final String STUDENT_COLUMN_MNAME = "student_mname";
    public static final String STUDENT_COLUMN_LNAME = "student_lname";
    public static final String STUDENT_COLUMN_PASSWORD = "student_password";
    public static final String STUDENT_COLUMN_GENDER = "student_gender";
    public static final String STUDENT_COLUMN_DATEREGISTERED = "student_dateRegister";
    public static final String STUDENT_COLUMN_PGNAME = "student_pgName";
    public static final String STUDENT_COLUMN_PGPHONE = "student_pgPhone";
    public static final String STUDENT_COLUMN_PGPOBOX = "student_pgPOBox";
    public static final String STUDENT_COLUMN_CLASS = "class_code";
    public static final String STUDENT_COLUMN_LOCATION = "location";

    public static final String LOCATION_TABLE_NAME = "table_location";
    public static final String LOCATION_COLUMN_ID ="_id";
    public static final String LOCATION_COLUMN_COUNTRY = "location_country";
    public static final String LOCATION_COLUMN_WORD = "location_word";
    public static final String LOCATION_COLUMN_REGION= "location_region";
    public static final String LOCATION_COLUMN_DISTRICT="location_district";


    public static final String CLASS_TABLE_NAME = "table_class";
    public static final String CLASS_COLUMN_CODE ="_id";
    public static final String CLASS_COLUMN_NAME ="class_name";
    public static final String CLASS_COLUMN_TEACHER ="class_teacher";

    public static final String ACADEMICSESION_TABLE_NAME = "table_AcademicSession";
    public static final String ACADEMIC_COLUMN_CODE ="_id";
    public static final String ACADEMIC_COLUMN_NAME ="academic_name";
    public static final String ACADEMIC_COLUMN_YEAR ="academic_year";

    public static final String SUBJECT_TABLE_NAME = "table_subject";
    public static final String SUBJECT_COLUMN_CODE ="_id";
    public static final String SUBJECT_COLUMN_NAME ="subject_name";
    public static final String SUBJECT_COLUMN_TEACHER ="subject_teacher";

    public static final String EXAMRESULT_TABLE_NAME = "table_ExamResult";
    public static final String EXAMRESULT_COLUMN_MARK ="exam_mark";
    public static final String EXAMRESULT_COLUMN_SUBJECT ="exam_subject";
    public static final String EXAMRESULT_COLUMN_STUDENT ="exam_student";
    public static final String EXAMRESULT_COLUMN_ACADEMIC ="exam_academic";

    public static final String  SUBJECTHASTEACHER_TABLE_NAME = "table_subject_has_table_teacher";
    public static final String  SUBJECTHASTEACHER_COLUMN_SUBJECT = "table_subject_subject_code";
    public static final String  SUBJECTHASTEACHER_COLUMN_TEACHER = "table_teacher_teacher_id";



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
//        ContentResolver myCR;
//        myCR = context.getContentResolver();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = myshuta.getWritableDatabase();
//            SQLiteQueryBuilder queryBuilderSubject = new SQLiteQueryBuilder();
//            queryBuilderSubject.setTables(DBHelper.SUBJECT_TABLE_NAME);


            int uriType = sURIMatcher.match(uri);
//        SQLiteDatabase db = myshuta.getWritableDatabase();

            switch (uriType) {
                case SUBJECT_ID:
//                    queryBuilderSubject.appendWhere(DBHelper.SUBJECT_COLUMN_CODE + "="
//                            + uri.getLastPathSegment());
//                   break;
                case LOCATION_ID:
                case TEACHER_ID:
                case ACADEMIC_ID:
                case CLASS_ID:
                case STUDENT_ID:
                    selection = selection + "_id = " + uri.getLastPathSegment();
                    break;
                case SUBJECT:
                case LOCATION:
                case TEACHER:
                case CLASS:
                case STUDENT:
                case ACADEMIC:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI");
            }
            Cursor cursor=db.query(uri.getLastPathSegment(),projection, selection, selectionArgs, null, null, sortOrder);

//            Cursor cursor = queryBuilderSubject.query(myshuta.getReadableDatabase(),
//                    projection, selection, selectionArgs, null, null, sortOrder);
//            cursor.setNotificationUri(getContext().getContentResolver(),
//                    uri);
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
                id = db.insert(SUBJECT_TABLE_NAME,null, values);
                break;
            case  TEACHER:
                id = db.insert(TEACHER_TABLE_NAME,null, values);
                break;
            case  STUDENT:
                id = db.insert(STUDENT_TABLE_NAME,null, values);
                break;
            case  LOCATION:
                id = db.insert(LOCATION_TABLE_NAME,null, values);
                break;
            case  ACADEMIC:
                id = db.insert(ACADEMICSESION_TABLE_NAME,null, values);
                break;
            case  CLASS:
                id = db.insert(CLASS_TABLE_NAME,null, values);
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
                rowsDeleted = sqlDB.delete(SUBJECT_TABLE_NAME, selection, selectionArgs);
                break;

            case SUBJECT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(SUBJECT_TABLE_NAME,
                            SUBJECT_COLUMN_CODE + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(SUBJECT_TABLE_NAME,
                            SUBJECT_COLUMN_CODE + "=" + id
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
                        sqlDB.update(SUBJECT_TABLE_NAME,
                                values,
                                selection,
                                selectionArgs);
                break;
            case SUBJECT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(SUBJECT_TABLE_NAME, values, SUBJECT_COLUMN_CODE + "=" + id, null);
                } else {
                    rowsUpdated =
                            sqlDB.update(SUBJECT_TABLE_NAME, values,SUBJECT_COLUMN_CODE + "=" + id + " and " + selection,
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


//    public Subject findProduct(String subjectname) {
//        String[] projection = {SUBJECT_COLUMN_CODE,
//                SUBJECT_COLUMN_NAME};
//
//        String selection = "subjectname = \"" + subjectname + "\"";
//
//        Cursor cursor = myCR.query(ShutaProvider.SUBJECT_URI,
//                projection, selection, null,
//                null);
//
//        Subject subject = new Subject();
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            subject.setSubjectCode(cursor.getString(0));
//            subject.setSubjectName(cursor.getString(1));
//            cursor.close();
//        } else {
//            subject = null;
//        }
//        return subject;
//    }

}
