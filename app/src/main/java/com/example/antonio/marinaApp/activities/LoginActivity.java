package com.example.antonio.marinaApp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.antonio.marinaApp.MainActivity;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.ulities.Helpers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.antonio.marinaApp.ulities.Helpers.onDoIntentTo;
import static com.example.antonio.marinaApp.ulities.Helpers.showMessage;

public class LoginActivity extends AppCompatActivity {

    String PASSWORD_KEY = "PASS";
    String EMAIL_KEy = "EMAIL";
    String LOGIN_STATUES = "STATUS";
    String TAG = LoginActivity.class.getName();
    @BindView(R.id.btn_server_login)
    Button btn_server_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.et_email)
    TextInputEditText et_email;
    @BindView(R.id.et_password)
    TextInputEditText et_password;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    private FirebaseAuth firebaseAuth;
    @VisibleForTesting
    public ProgressDialog progressDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Helpers.getLoggingStatus(LoginActivity.this, LOGIN_STATUES)) {

            checkbox.setChecked(true);
            et_email.setText(Helpers.getLoginFeilds(this, EMAIL_KEy));
            et_password.setText(Helpers.getLoginFeilds(this, PASSWORD_KEY));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.btn_register, R.id.btn_server_login})
    void aVoid(View view) {

        switch (view.getId()) {

            case R.id.btn_server_login:
                if (isValidForm()) {
                    if (checkbox.isChecked()) {
                        Helpers.saveLoginFeilds(LoginActivity.this, PASSWORD_KEY, et_password.getText().toString().trim());
                        Helpers.saveLoginFeilds(LoginActivity.this, EMAIL_KEy, et_email.getText().toString().trim());
                        Helpers.saveLoggingStatus(LoginActivity.this, LOGIN_STATUES, true);

                    }else {
                        Helpers.saveLoggingStatus(LoginActivity.this, LOGIN_STATUES, false);

                    }
                    performLoginOrAccountCreation(et_email.getText().toString(), et_password.getText().toString());
                }
                break;
            case R.id.btn_register:
                onDoIntentTo(this, NewMemberToFirebaseActivity.class);
                break;


        }

    }

    private boolean isValidForm() {
        if (et_email.getText().toString().isEmpty()) {
            showMessage(this, "من فضلك ادخل الايميل");
            return false;
        }
        if (et_password.getText().toString().isEmpty()) {
            showMessage(this, "من فضلك ادخل الرقم السري");
            return false;
        }

        return true;
    }

    private void performLoginOrAccountCreation(final String email, final String password) {
        showProgressDialog();
        firebaseAuth.fetchProvidersForEmail(email).addOnCompleteListener(
                this, new OnCompleteListener<ProviderQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "checking to see if user exists in firebase or not");
                            ProviderQueryResult result = task.getResult();

                            if (result != null && result.getProviders() != null
                                    && result.getProviders().size() > 0) {
                                Log.d(TAG, "User exists, trying to login using entered credentials");

                                performLogin(email, password);
                            } else {
                                Log.d(TAG, "User doesn't exist, creating account");
                                showMessage(LoginActivity.this, "الايميل غير موجود");
                                //registerAccount(email, password);
                            }
                        } else {
                            Log.w(TAG, "User check failed", task.getException());
                            showMessage(LoginActivity.this, "خطأ فى الرقم السري او الايميل ");

                        }
                        //hide progress dialog
                        hideProgressDialog();
                        //enable and disable login, logout buttons depending on signin status
                        //showAppropriateOptions();
                    }
                });
    }

    private void performLogin(String email, String password) {
        showProgressDialog();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "login success");
                            showMessage(LoginActivity.this, "تم التسجيل بنجاح");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.e(TAG, "Login fail", task.getException());
                            showMessage(LoginActivity.this, " حدث خطأ حاول مرة ثانية");
                        }
                        //hide progress dialog
                        hideProgressDialog();
                        //enable and disable login, logout buttons depending on signin status
                        // showAppropriateOptions();
                    }
                });
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("من فضلك انتظر...");
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
