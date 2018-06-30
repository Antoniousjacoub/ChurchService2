package com.example.antonio.marinaApp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.antonio.marinaApp.MainActivity;
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
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.antonio.marinaApp.ulities.Helpers.showMessage;

public class UserInfoDetailsActivity extends AppCompatActivity {

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

    @BindView(R.id.img_calendar)
    ImageView img_calender;
    Unbinder unbinder;
    private File mFile_Profile_photo;
    private int PROFILE_PHOTO=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_details);

        unbinder= ButterKnife.bind(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    private void postDataToFirebase(){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef = rootRef.child("user").push();
        User user =new User();
        user.setFirst_name("Toni");
        user.setBithdate("18-8-1995");

        yourRef.setValue(user);

    }

    @OnClick({R.id.btn_cancel,R.id.btn_save,R.id.img_calendar,R.id.user_img_profile})
    void aVoid(View view){
        switch (view.getId()){
            case R.id.user_img_profile:
                openGallery(PROFILE_PHOTO);
                break;

            case R.id.btn_save:
             if (validForm()){
                 uploadImage();
                 postDataToFirebase();
               }
                break;


            case R.id.btn_cancel:
                finish();
                break;
            case R.id.img_calendar:
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

    private void uploadImage() {

        if(mFile_Profile_photo != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("جاري التحميل");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(Uri.fromFile(mFile_Profile_photo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                          showMessage(UserInfoDetailsActivity.this, "تم ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
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
