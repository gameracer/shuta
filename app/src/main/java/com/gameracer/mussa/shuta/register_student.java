package com.gameracer.mussa.shuta;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class register_student extends AppCompatActivity {

    private RadioGroup sex;
    private RadioButton selSex;
    private Button save;
    private EditText fname;
    private EditText mname;
    private EditText lname;
    private EditText dateOfbirth;
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int CHOOSE_PIC_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;

    private Uri mMediaUri;

    private Button imageUpload;
    private Button clearImage;
    protected ImageView mPreviewImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        initView();
    }

    public void initView() {
        sex = findViewById(R.id.sex);
        fname = findViewById(R.id.fname);
        mname = findViewById(R.id.mname);
        lname = findViewById(R.id.lname);
        save = findViewById(R.id.save);
        dateOfbirth = findViewById(R.id.vDoB);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"one", "two", "three", "four", "five", "six"};//item for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        imageUpload = (Button) findViewById(R.id.vImageUpload);
        clearImage=(Button) findViewById(R.id.vRemoveImage);
        mPreviewImageView = (ImageView) findViewById(R.id.vImageProfile);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = sex.getCheckedRadioButtonId();
                selSex = findViewById(selectedId);
                String fn = fname.getText().toString();
                String mn = mname.getText().toString();
                String ln = lname.getText().toString();
                String gender = selSex.getText().toString();
                String DoB = dateOfbirth.getText().toString();
                Log.e(DoB, "date of birth");
                saveAction(fn, mn, ln, gender, DoB);

            }
        });

        //Change profile image
        //set onlClick to TextView
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Upload Pic Pressed", Toast.LENGTH_SHORT).show();

                //show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(register_student.this);
                builder.setTitle("Upload or Take a photo");
                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //upload image
                        Intent choosePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        choosePictureIntent.setType("image/*");
                        startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE);

                    }
                });
                builder.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //take photo
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                        startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE);

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });//End change profile image onClick Listener

        clearImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviewImageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
            }
        });


    }//end initView


    public void saveAction(String fn, String mn, String ln, String sex, String Dob) {
        Toast.makeText(getApplicationContext(), "name: " + fn + " " + ln + " sex: " + sex + "DoB: " + Dob, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();
    }

    //check if external storage is mounted. helper method
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_PIC_REQUEST_CODE) {
                if (data == null) {
                    Toast.makeText(getApplicationContext(), "Image cannot be null!", Toast.LENGTH_LONG).show();
                } else {
                    mMediaUri = data.getData();
                    //set previews
                    mPreviewImageView.setImageURI(mMediaUri);

                }
            } else {
                //set previews
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");


                mPreviewImageView.setImageBitmap(imageBitmap);

            }

        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_LONG).show();
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    String mCurrentPhotoPath;
    private void setPic() {

        // Get the dimensions of the View
        int targetW = mPreviewImageView.getWidth();
        int targetH = mPreviewImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mPreviewImageView.setImageBitmap(bitmap);
    }

}
