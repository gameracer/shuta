package com.gameracer.mussa.shuta;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.gameracer.mussa.shuta.provider.ShutaProvider;
import com.gameracer.mussa.shuta.provider.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class StudentRegister extends AppCompatActivity {
    private EditText fname, mname, lname, pgname, pgPhoneNo, pgPoBox, password;
    private RadioGroup genderGroup;
    private RadioButton gender;
    String gender1;
    Button dateOfBirth,save;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    RadioButton studentMale,studentFemale;

    //    private Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        initView();

    }


    public void initView() {
        dateOfBirth = (Button) findViewById(R.id.dateOfBirth);
        fname = (EditText) findViewById(R.id.studentFname);
        mname = (EditText) findViewById(R.id.studentMname);
        lname = (EditText) findViewById(R.id.studentLname);
        password = (EditText) findViewById(R.id.studentPassword);
        studentMale=(RadioButton)findViewById(R.id.studentMale); 
        studentFemale=(RadioButton)findViewById(R.id.studentFemale);
        lname = (EditText) findViewById(R.id.studentLname);
        pgname = (EditText) findViewById(R.id.studentLname);
        pgPhoneNo = (EditText) findViewById(R.id.studentLname);
        pgPoBox = (EditText) findViewById(R.id.studentLname);
        lname = (EditText) findViewById(R.id.studentLname);
        save=(Button) findViewById(R.id.saveStudent);


        Spinner spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
        final Spinner spinnerDistrict = (Spinner) findViewById(R.id.spinnerDistrict);
        final Spinner spinnerWard = (Spinner) findViewById(R.id.spinnerWard);


        String[] lstRegion = {ShutaProvider.LOCATION_COLUMN_REGION};
        Cursor regionlistCursor = getContentResolver().query(ShutaProvider.LOCATION_URI, lstRegion, null, null, null);
        ArrayList<String> regionArrayList = new ArrayList<String>();
        //load array
        regionArrayList.add("select Region");
        while (regionlistCursor.moveToNext()) {
            regionArrayList.add(regionlistCursor.getString(regionlistCursor.getColumnIndex(ShutaProvider.LOCATION_COLUMN_REGION))); //add the item
        }
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regionArrayList);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(regionAdapter);

//district
        final ArrayList<String> districtArrayList = new ArrayList<String>();
        //load array
        districtArrayList.add("select district");
        final ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtArrayList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(districtAdapter);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] selectionArgs = {};
                String region;
                if (position > 0) {
                    region = parent.getSelectedItem().toString();
                    selectionArgs = new String[]{region};
                }
                String[] lstDistrict = {ShutaProvider.LOCATION_COLUMN_DISTRICT};
                String selection = "location_region = ?";
                Cursor districtlistCursor = getContentResolver().query(ShutaProvider.LOCATION_URI, lstDistrict, selection, selectionArgs, null);
                districtArrayList.clear();
                districtArrayList.add("select district");
                while (districtlistCursor.moveToNext()) {
                    districtArrayList.add(districtlistCursor.getString(districtlistCursor.getColumnIndex(ShutaProvider.LOCATION_COLUMN_DISTRICT))); //add the item
                }
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(districtAdapter);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //spinner for ward
        final ArrayList<String> wardArrayList = new ArrayList<String>();
        //load array
        wardArrayList.add("select word");
        final ArrayAdapter<String> wardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wardArrayList);
        wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWard.setAdapter(wardAdapter);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] selectionArgs = {};
                if (position > 0) {

                    String district = parent.getSelectedItem().toString();
                    selectionArgs = new String[]{district};
                    String[] lstWard = {ShutaProvider.LOCATION_COLUMN_WORD};
                    String selection = "location_district = ?";
                    wardArrayList.clear();
                    wardArrayList.add("select word");
                    Cursor wardlistCursor = getContentResolver().query(ShutaProvider.LOCATION_URI, lstWard, selection, selectionArgs, null);

                    while (wardlistCursor.moveToNext()) {
                        wardArrayList.add(wardlistCursor.getString(wardlistCursor.getColumnIndex(ShutaProvider.LOCATION_COLUMN_WORD))); //add the item
                    }
                    wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerWard.setAdapter(wardAdapter);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(StudentRegister.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
//           datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAction(fname,mname,lname,password,gender1,pgname,pgPhoneNo,pgPoBox,null,null,0);
            }
        });

    }//end of init view

    public void saveAction(EditText fname, EditText mname, EditText lname,
                           EditText password, String gender, EditText pgname,
                           EditText pgPhoneNo, EditText pgPoBox,
                           @Nullable EditText stClass, @Nullable EditText location, int location_id) {
        String studentID,fnm, mnm,lnm,pwd, pgnm,pgPhNo,pgPBox;
        int Id;
        fnm=fname.getText().toString();
        mnm=mname.getText().toString();
        lnm=lname.getText().toString();
        pwd=password.getText().toString();
        pgnm=pgname.getText().toString();
        pgPhNo=pgPhoneNo.getText().toString();
        pgPBox=pgPoBox.getText().toString();

        Random number=new Random();
        int id = number.nextInt(5000);
        studentID="2018-"+id;

        Student student=new Student(studentID,fnm,mnm,lnm,pwd,gender,pgnm,pgPhNo,pgPBox,null,null);
        addStudent(student);


    }

    public void addStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(ShutaProvider.STUDENT_COLUMN_ID, student.getID());
        values.put(ShutaProvider.STUDENT_COLUMN_FNAME, student.getFname());
        values.put(ShutaProvider.STUDENT_COLUMN_MNAME, student.getMname());
        values.put(ShutaProvider.STUDENT_COLUMN_LNAME, student.getLname());
        values.put(ShutaProvider.STUDENT_COLUMN_GENDER, student.getGender());
        values.put(ShutaProvider.STUDENT_COLUMN_PASSWORD, student.getPassword());
        values.put(ShutaProvider.STUDENT_COLUMN_PGNAME, student.getPgname());
        values.put(ShutaProvider.STUDENT_COLUMN_PGPHONE, student.getPgPhoneNo());
        values.put(ShutaProvider.STUDENT_COLUMN_PGPOBOX, student.getPgPoBox());
//        values.put(ShutaProvider.STUDENT_COLUMN_LOCATION, student.getLocation_id());

        getContentResolver().insert(ShutaProvider.STUDENT_URI, values);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.studentMale:
                if (checked) {
                    gender1 =  studentMale.getText().toString();
                }
                    break;
            case R.id.studentFemale:
                if (checked)
                    gender1 =  studentFemale.getText().toString();
                    break;
        }

    }
}
