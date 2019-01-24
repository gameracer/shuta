package com.gameracer.mussa.shuta.provider;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.Contacts.SettingsColumns.KEY;
import static java.sql.Types.NULL;
import static java.sql.Types.VARCHAR;
import static java.text.Collator.PRIMARY;

public class DBHelper extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME = "shuta.db";

//    //tablename and colum
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


    //
//    //table 2 :pg=parent or garadian
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
                "  _id INT PRIMARY KEY AUTOINCREMENT,\n" +
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
    public void addSubject(Subject subject) {

        ContentValues values = new ContentValues();
        values.put(SUBJECT_COLUMN_CODE, subject.getSubjectCode());
        values.put(SUBJECT_COLUMN_NAME, subject.getSubjectName());

        myCR.insert(ShutaProvider.SUBJECT_URI, values);
    }
    public Subject findProduct(String subjectname) {
        String[] projection = {SUBJECT_COLUMN_CODE,
                SUBJECT_COLUMN_NAME};

        String selection = "subjectname = \"" + subjectname + "\"";

        Cursor cursor = myCR.query(ShutaProvider.SUBJECT_URI,
                projection, selection, null,
                null);

        Subject subject = new Subject();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            subject.setSubjectCode(cursor.getString(0));
            subject.setSubjectName(cursor.getString(1));
            cursor.close();
        } else {
            subject = null;
        }
        return subject;
    }
    public Cursor getAllSubject(){
        String[] projection = {SUBJECT_COLUMN_CODE,
                SUBJECT_COLUMN_NAME};
        Cursor mcursor=myCR.query(ShutaProvider.SUBJECT_URI,projection,null,null,null);
        return mcursor;
    }
    public void addLocation(Location location) {

        ContentValues values = new ContentValues();
        values.put(LOCATION_COLUMN_ID,location.getID());
        values.put(LOCATION_COLUMN_REGION, location.getRegion());
        values.put(LOCATION_COLUMN_DISTRICT, location.getDistrict());
        values.put(LOCATION_COLUMN_WORD, location.getWord());


        myCR.insert(ShutaProvider.SUBJECT_URI, values);
    }
    public void addTeacher(Teacher teacher) {

        ContentValues values = new ContentValues();
        values.put(TEACHER_COLUMN_ID, teacher.getId() );
        values.put(TEACHER_COLUMN_FNAME,teacher.getFname() );
        values.put(TEACHER_COLUMN_MNAME,teacher.getMname() );
        values.put(TEACHER_COLUMN_LNAME,teacher.getLname() );
        values.put(TEACHER_COLUMN_PASSWORD, teacher.getPassword());
        values.put(TEACHER_COLUMN_GENDER, teacher.getGender());
        values.put(TEACHER_COLUMN_EMAIL,teacher.getEmail() );
        values.put(TEACHER_COLUMN_PHONENO, teacher.getPhoneNo());
        values.put(TEACHER_COLUMN_LOCATION, teacher.getLocationID() );
        myCR.insert(ShutaProvider.SUBJECT_URI, values);
    }
    public void addAcademic(Academic academic) {

        ContentValues values = new ContentValues();
        values.put(ACADEMIC_COLUMN_CODE, academic.getAcademicID());
        values.put(ACADEMIC_COLUMN_NAME, academic.getName());
        values.put(ACADEMIC_COLUMN_YEAR, academic.getYear());

        myCR.insert(ShutaProvider.SUBJECT_URI, values);
    }
    public void addClass(Class mclass) {

        ContentValues values = new ContentValues();
        values.put(ACADEMIC_COLUMN_NAME, mclass.getName());
        myCR.insert(ShutaProvider.SUBJECT_URI, values);
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
