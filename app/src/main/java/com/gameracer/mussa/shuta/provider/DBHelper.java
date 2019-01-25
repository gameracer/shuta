package com.gameracer.mussa.shuta.provider;

import java.util.HashMap;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME = "shuta.db";

//    //tablename and colum


    private HashMap hp;
    private ContentResolver myCR;
    public DBHelper(Context context) {

        super(context, DATABASE_NAME , null, 1);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        //create table location
        db.execSQL("CREATE TABLE table_location (\n" +
                "  _id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  location_word VARCHAR(45) ,\n" +
                "  location_region VARCHAR(45) ,\n" +
                "  location_discrict VARCHAR(45) )");

        //create table teacher
        db.execSQL("CREATE TABLE  table_teacher (\n" +
                "  _id VARCHAR(45) PRIMARY KEY,\n" +
                "  teacher_fname VARCHAR(45) ,\n" +
                "  teacher_mname VARCHAR(45) ,\n" +
                "  teacher_lname VARCHAR(45) ,\n" +
                "  teacher_gender VARCHAR(45) ,\n" +
                "  teacher_PhoneNo VARCHAR(45) ,\n" +
                "  teacher_Email VARCHAR(45) ,\n" +
                "  teacher_location INT ,\n" +
                "    FOREIGN KEY (`teacher_location`)\n" +
                "    REFERENCES table_location(_id) )");

        //create table class
        db.execSQL("CREATE TABLE IF NOT EXISTS table_class (\n" +
                "  _id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  `class_name` VARCHAR(45) ,\n" +
                "  `class_teacher` VARCHAR(45) ," +
                "    FOREIGN KEY (`class_teacher`) " +
                "REFERENCES table_teacher (`_id`) )");

        //create table student
        db.execSQL("CREATE TABLE IF NOT EXISTS table_student (\n" +
                "  _id VARCHAR(45) PRIMARY KEY ,\n" +
                "  `student_fname` VARCHAR(45) ,\n" +
                "  `student_mname` VARCHAR(45) ,\n" +
                "  `student_lname` VARCHAR(45) ,\n" +
                "  `student_password` VARCHAR(45) ,\n" +
                "  `student_gender` VARCHAR(45) ,\n" +
                "  `student_dateRegistered` DATE ,\n" +
                "  `student_pgName` VARCHAR(45) ,\n" +
                "  `student_pgPhoneNo` VARCHAR(45) ,\n" +
                "  `student_pgPOBox` VARCHAR(45) ,\n" +
                "  `student_class` VARCHAR(45) ,\n"+
                "   `student_location` INT ,\n"+
                "    FOREIGN KEY (`student_class`)\n" +
                "    REFERENCES table_class (_id),\n" +
                "    FOREIGN KEY ('student_location')\n" +
                "    REFERENCES table_location (`_id`))");

        //create table _subject
        db.execSQL("CREATE TABLE IF NOT EXISTS table_subject (\n" +
                "  _id VARCHAR(45) PRIMARY KEY,\n" +
                "  `subject_name` VARCHAR(45),\n" +
                "  `subject_teacher` VARCHAR(45),\n"+
                "    FOREIGN KEY (`subject_teacher`)\n" +
                "    REFERENCES table_teacher (teacher_id) )");

        //create academic session
        db.execSQL("CREATE TABLE IF NOT EXISTS table_AcademicSession (\n" +
                "  _id VARCHAR(45) PRIMARY KEY,\n" +
                "  `academic_name` VARCHAR(45) ,\n" +
                "  `academic_year` INT )");

        //create table_ExamResult
        db.execSQL("CREATE TABLE IF NOT EXISTS table_ExamResult (\n" +
                "  `exam_mark` INT ,\n" +
                "  `exam_class` VARCHAR(45),\n"+
                "  `exam_student` VARCHAR(45) NOT NULL,\n"+
                "  `exam_subject` VARCHAR(45) NOT NULL,\n"+
                "  `exam_academic` VARCHAR(45) NOT NULL,\n"+
                "PRIMARY KEY (`exam_student`, `exam_subject`, `exam_academic`),\n"+
                "    FOREIGN KEY ('exam_subject')\n" +
                "    REFERENCES table_subject (`_id`),\n" +
                "    FOREIGN KEY (`exam_student`)\n" +
                "    REFERENCES table_student (`_id`),\n" +
                "    FOREIGN KEY (`exam_academic`)\n" +
                "    REFERENCES table_AcademicSession (`_id`),\n" +
                "    FOREIGN KEY (`exam_class`)\n" +
                "    REFERENCES table_class (`_id`) )");

        //create table_subject_teacher
        db.execSQL("CREATE TABLE IF NOT EXISTS table_subject_has_table_teacher (\n" +
                "  `table_subject_subject_code` VARCHAR(45) NOT NULL,\n" +
                "  `table_teacher_teacher_id` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`table_subject_subject_code`, `table_teacher_teacher_id`),\n" +
                "    FOREIGN KEY (`table_subject_subject_code`)\n" +
                "    REFERENCES table_subject (`_id`),\n" +
                "    FOREIGN KEY (`table_teacher_teacher_id`)\n" +
                "    REFERENCES `table_teacher` (`_id`) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
//        db.execSQL("DROP TABLE IF EXISTS table_subject");
                onCreate(db);
    }

//    public boolean insertUsers (String name, String role) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("role", role);
//        db.insert("Users", null, contentValues);
//        return true;
//    }

//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from Users where id"+id, null );
//        return res;
//    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
//        return numRows;
//    }

//    public boolean updateUsers (Integer id, String name, String phone) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("role", phone);
//        db.update("Users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }

//    public Integer deleteUsers (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("Users",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<String> getAllUsers() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
////        hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from Users", null );
//       // res.moveToFirst();
//
//        while(res.moveToNext()){
//            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_ID)));
//            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
//            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_ROLE)));
//            }
//        return array_list;
//    }
}
