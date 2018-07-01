package com.example.antonio.marinaApp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.antonio.marinaApp.ulities.Helpers.showMessage;

public class UserInfoDetailsActivity extends AppCompatActivity {

    String TAG=UserInfoDetailsActivity.class.getName();
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @BindView(R.id.user_img_profile)
    ImageView user_img_profile;

    @BindView(R.id.et_first_name)
    EditText et_first_name;

    @BindView(R.id.edt_email)
   EditText edt_email;

    @BindView(R.id.et_last_name)
   EditText et_last_name;

    @BindView(R.id.et_middle_name)
   EditText et_middle_name;
    @BindView(R.id.rd_male)
    RadioButton rd_male;

    @BindView(R.id.rd_female)
    RadioButton rd_female;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;


    @BindView(R.id.txt_birthdate)
    TextView txt_birthdate;

    @BindView(R.id.img_calendar)
    ImageView img_calender;
    Unbinder unbinder;
    private File mFile_Profile_photo;
    private int PROFILE_PHOTO=100;
    private final int DIALOG_ID_BirthDate=0;
    int cur = 0;
    int year_x, month_x, day_x;
    String setToday;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_details);

        unbinder= ButterKnife.bind(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        year_x = myCalendar.get(Calendar.YEAR);
        month_x = myCalendar.get(Calendar.MONTH);
        day_x = myCalendar.get(Calendar.DAY_OF_MONTH);
        String myFormat = "yyyy-MM-dd"; //In which format  you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        setToday = sdf.format(myCalendar.getTime());

    }



    private void openCalendar() {
        showDialog(DIALOG_ID_BirthDate);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID_BirthDate:
                System.out.println("onCreateDialog  : " + id);
                cur = DIALOG_ID_BirthDate;
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);

//                DatePickerDialog datePickerDialog = this.customDatePicker();
//                return datePickerDialog;

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which format  you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("TAG", "updateLabel: " + sdf.format(myCalendar.getTime()));

        if (CheckDates(setToday, sdf.format(myCalendar.getTime()))) {
            if (cur == DIALOG_ID_BirthDate) {

                SpannableString spannableStringObject = new SpannableString(sdf.format(myCalendar.getTime()));
                spannableStringObject.setSpan(new UnderlineSpan(), 0, spannableStringObject.length(), 0);
                txt_birthdate.setText(spannableStringObject);
            }
        } else {

            Toast.makeText(getApplicationContext(), "من فضلك أختر تاريخ ميلاد صالح", Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean CheckDates(String todayDate, String selectDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

        boolean b = false;
        try {
            if (dfDate.parse(selectDate).before(dfDate.parse(todayDate))) {
                b = true;//If start date is before end date
            } else if (dfDate.parse(selectDate).equals(dfDate.parse(todayDate))) {
                b = true;//If two dates are equal
            } else {
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }

    @OnClick({R.id.btn_cancel,R.id.btn_save,R.id.img_calendar,R.id.user_img_profile})
    void aVoid(View view){
        switch (view.getId()){
            case R.id.user_img_profile:
                openGallery(PROFILE_PHOTO);
                break;

            case R.id.btn_save:
             if (validForm()){
                 uploadImage(new HandleResponseUploadedImage() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                         DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                         DatabaseReference yourRef = rootRef.child("user").push();
                         User user =new User();
                         user.setFirst_name(et_first_name.getText().toString().trim());
                         if (!txt_birthdate.getText().toString().isEmpty())
                             user.setBithdate(txt_birthdate.getText().toString());

                         user.setProfile_image_url(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                         Log.e(TAG,"Profile_URL_uri>>>>"+ taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());


                         yourRef.setValue(user);

                     }

                     @Override
                     public void onFailure(Exception e) {

                     }
                 });

               }
                break;


            case R.id.btn_cancel:
                finish();
                break;
            case R.id.img_calendar:
                openCalendar();
                break;


        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //start upload image cover

        if (resultCode == Activity.RESULT_OK&&data!=null) {

            // if (requestCode == EasyImage.RequestCodes.PICK_PICTURE_FROM_GALLERY) {
            EasyImage.handleActivityResult(requestCode, resultCode, data, UserInfoDetailsActivity.this, new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //Some error handling


                }

                @Override
                public void onImagesPicked(@NonNull List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                    //Handle the images

                    if (type == PROFILE_PHOTO) {
                        mFile_Profile_photo = imagesFiles.get(0);


                    }


                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    // Cancel handling, you might wanna remove taken photo if it was canceled
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(UserInfoDetailsActivity.this);
                        if (photoFile != null) photoFile.delete();
                    }
                }
            });


            //   }

            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void uploadImage(final HandleResponseUploadedImage handleResponseUploadedImage) {

        if(mFile_Profile_photo != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("جاري التحميل");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ Uri.fromFile(mFile_Profile_photo).getLastPathSegment());
            ref.putFile(Uri.fromFile(mFile_Profile_photo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            handleResponseUploadedImage.onSuccess(taskSnapshot);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            handleResponseUploadedImage.onFailure(e);
                           //showMessage(UserInfoDetailsActivity.this, "Failed "+e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("تم "+(int)progress+"%");
                        }
                    });
        }
    }

    private interface HandleResponseUploadedImage {
        void onSuccess(UploadTask.TaskSnapshot taskSnapshot);
        void onFailure( Exception e);
    }
    public void openGallery(int req_code) {

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.e("opencamera", "true");
            EasyImage.openChooserWithGallery(this, "اختر صورة", req_code);
        } else {
            Log.e("opencamera", "false");
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, null,
                    req_code, perms);
        }
    }
    private boolean validForm(){


        if (et_first_name.getText().toString().trim().isEmpty()){
            showMessage(this,"من فضلك ادخل اسمك ");
            return false;

        }

        if (!rd_male.isChecked()&&!rd_female.isChecked()){
            showMessage(this,"من فضلك اختر ذكر او انثي");
            return false;

        }

    return true;
    }
}
