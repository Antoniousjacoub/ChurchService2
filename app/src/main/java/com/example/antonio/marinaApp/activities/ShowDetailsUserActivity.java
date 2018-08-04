package com.example.antonio.marinaApp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.models.User;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowDetailsUserActivity extends AppCompatActivity {

    public static String USER_INFO="USERINFO";
    @BindView(R.id.user_img_profile)
    ImageView user_img_profile;

    @BindView(R.id.et_first_name)
    EditText et_first_name;

    @BindView(R.id.edt_email)
    EditText edt_email;

    @BindView(R.id.et_notes)
    EditText et_notes;

    @BindView(R.id.et_middle_name)
    EditText et_middle_name;

      @BindView(R.id.et_gender)
    EditText et_gender;

    @BindView(R.id.txt_birthdate)
    EditText txt_birthdate;

    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_user);
        unbinder= ButterKnife.bind(this);

        Bundle bundle=getIntent().getExtras();

        if (bundle!=null){
            Gson gson=new Gson();
            User user =gson.fromJson(bundle.getString(USER_INFO),User.class);
            if (user!=null)
            setDataInView(user);
        }
    }

    private void setDataInView(User user){

        if (user.getProfile_image_url()!=null) {
            Glide.with(this)

                    .load(user.getProfile_image_url())
                    .into(user_img_profile);
        }

        if (user.getFirst_name()!=null){

            et_first_name.setText(user.getFirst_name());
        }else {
            et_first_name.setText("لا يوجد");
        }
        if (user.getMidle_name()!=null){

            et_middle_name.setText(user.getMidle_name());
        }else {

            et_middle_name.setText("لا يوجد");
        }

        if (user.getBithdate()!=null){
            txt_birthdate.setText(user.getBithdate());
        }else {

            txt_birthdate.setText("لا يوجد");
        }

        if (user.getGender()!=null){

            et_gender.setText(user.getGender());
        }else {

            et_gender.setText("لا يوجد");
        }
        if (user.getNotes()!=null){

            et_notes.setText(user.getNotes());
        }else {

            et_notes.setText("لا يوجد");
        }
        if (user.getEmail()!=null){

            edt_email.setText(user.getEmail());
        }else {

            edt_email.setText("لا يوجد");
        }

    }

}
