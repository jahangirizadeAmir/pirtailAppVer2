package com.pirtail.piratilgame.Gamer.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.R;


public class Main2Activity extends AppCompatActivity {

    EditText edt_username, edt_phone_number, edt_name_family, reagent_phone_number;
    RadioButton rbtn_men, rbtn_women;
    Button btn_send;
    String username, UserPhoneNumber,reagent , nameFamily, gender, token, mobile;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    CustomToast customToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        defineObjects();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Empty the name and family
        if (nameFamily.equals("")){
            customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyNameFamilyError), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
            customToast.getToast().show();
        }

        // checked phoneNumber
        if (UserPhoneNumber.equals("") && UserPhoneNumber.length()<11 && UserPhoneNumber.length()>11){
            customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.incompatiblePhoneNUmber), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
            customToast.getToast().show();
        }

        // checked gender
        if (!rbtn_men.isChecked() && !rbtn_women.isChecked()){
            customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyGender), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
            customToast.getToast().show();
        }

        //  Empty Reagent phone number
        if (UserPhoneNumber.equals("") && UserPhoneNumber.length()<11 && UserPhoneNumber.length()>11){
            customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.incompatiblePhoneNUmber), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
            customToast.getToast().show();
        }

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reagent_phone_number.getText().toString().trim().equals(mobile)){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.wrongReagentPhoneNumber), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }
                else {

                }
            }
        });

    }

    private void defineObjects() {
        edt_username=(EditText)findViewById(R.id.edt_username);
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_send=(Button) findViewById(R.id.btn_send);
        reagent_phone_number=(EditText)findViewById(R.id.reagent_phone_number);
        edt_name_family=(EditText)findViewById(R.id.edt_name_family);
        rbtn_men=(RadioButton) findViewById(R.id.rbtn_men);
        rbtn_women=(RadioButton) findViewById(R.id.rbtn_women);

        username = edt_username.getText().toString().trim();
        UserPhoneNumber = edt_phone_number.getText().toString().trim();
        reagent = reagent_phone_number.getText().toString().trim();
        nameFamily = edt_name_family.getText().toString().trim();
        if (rbtn_men.isChecked()){
            gender="1";
        }else {
            gender="0";
        }

        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        mobile = sharedPreferences.getString("mobile", null);
    }
}
