package com.example.antonio.marinaApp.activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.antonio.marinaApp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.antonio.marinaApp.ulities.Helpers.showMessage;

public class NewMemberToFirebaseActivity extends AppCompatActivity {


    @BindView(R.id.et_first_name)
    EditText et_first_name;

    @BindView(R.id.edt_email)
    EditText edt_email;

    String TAG = NewMemberToFirebaseActivity.class.getName();


    @BindView(R.id.rd_male)
    RadioButton rd_male;

    @BindView(R.id.rd_female)
    RadioButton rd_female;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_repeat_password)
    EditText edt_repeat_password;


    @VisibleForTesting
    public ProgressDialog progressDialog;
    Unbinder unbinder;
    private File mFile_Profile_photo;
    private int PROFILE_PHOTO = 100;
    private final int DIALOG_ID_BirthDate = 0;
    int cur = 0;
    int year_x, month_x, day_x;
    String setToday;
    Calendar myCalendar = Calendar.getInstance();
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member_to_firebase);

        unbinder = ButterKnife.bind(this);
firebaseAuth=FirebaseAuth.getInstance();

    }

    @OnClick({R.id.btn_save})
    void aVoid(View view) {
        switch (view.getId()) {

            case R.id.btn_save:
                if (isValid())
                    registerAccount(edt_email.getText().toString(), edt_password.getText().toString());
                break;

        }

    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait!");
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void registerAccount(String email, String password) {
        showProgressDialog();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "account created");
                            showMessage(NewMemberToFirebaseActivity.this,
                                    "تم التسجيل بنجاح");
                            finish();

                        } else {
                            Log.d(TAG, "register account failed", task.getException());
                            try {

                                if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                    showMessage(NewMemberToFirebaseActivity.this, "الايميل مسجل من قبل");
                                }else if (task.getException().getMessage().equals("The email address is badly formatted.")){
                                    showMessage(NewMemberToFirebaseActivity.this, "من فضلك اكتب الايميل صحيحا");
                                }
                                else {
                                    showMessage(NewMemberToFirebaseActivity.this, "حدث خطأ حاول ثانيا");
                                }
                            }catch (Exception e){

                            }
                        }
                        //hide progress dialog
                        hideProgressDialog();
                    }
                });
    }

    private boolean isValid() {
        if (et_first_name.getText().toString().isEmpty()) {

            showMessage(this, "من فضلك ادخل الاسم");
            return false;

        }
        if (edt_email.getText().toString().isEmpty()) {
            showMessage(this, "من فضلك ادخل الايميل");
            return false;
        }
        if (edt_password.getText().toString().trim().isEmpty()){
            showMessage(this, "من فضلك ادخل الرقم السري");
            return false;
        }

        if (edt_repeat_password.getText().toString().trim().isEmpty()){
            showMessage(this, "من فضلك اعد كتابة الرقم السري");
            return false;
        }

        if (!edt_password.getText().toString().equals(edt_repeat_password.getText().toString())) {
            showMessage(this, "الرقم السري غير متطابق");
            return false;
        }
        if(edt_password.getText().toString().trim().length()<6){
            showMessage(this, "الرقم السري يجب ان يكون اكبر من 6 ارقام ");
            return false;

        }

        return true;
    }

}
