package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Register extends AppCompatActivity {

    EditText edt_phone_number, edt_name_family, reagent_phone_number;
    RadioButton rbtn_men, rbtn_women;
    Button btn_conformation;
    String username, regenantPhoneNumber,reagent , nameFamily, gender, token, mobile, encryptedToken;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    CustomToast customToast;
    JSONObject jsonObject;
    AlertDialog.Builder builder;
    Intent intent;
    HashMap<String,String> stringStringHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        defineObjects();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //sending data to server process
        btn_conformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regenantPhoneNumber = reagent_phone_number.getText().toString().trim();
                nameFamily = edt_name_family.getText().toString().trim();

                Toast.makeText(Register.this, ""+nameFamily, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Register.this, ""+reagent, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Register.this, ""+regenantPhoneNumber, Toast.LENGTH_SHORT).show();

                if (rbtn_men.isChecked()){
                    gender="1";
                }else {
                    gender="0";
                }

                // Empty the name and family ERROR
                if (nameFamily.equals("")){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyNameFamilyERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }


                // Empty gender ERROR
                if (!rbtn_men.isChecked() && !rbtn_women.isChecked()){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyGenderERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }

                //  Empty Reagent phone number ERROR
                if (regenantPhoneNumber.equals("") && regenantPhoneNumber.length()<11 && regenantPhoneNumber.length()>11){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.incompatiblePhoneNUmberERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }

                //similarity regnant phone number with user phone number ERROR
                if (reagent_phone_number.getText().toString().trim().equals(mobile)){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.wrongReagentPhoneNumberERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }
                else {
                    stringStringHashMap = new HashMap<>();
//                            stringStringHashMap.put("appVersion","1");
//                            stringStringHashMap.put("device","android");
                    stringStringHashMap.put("mobile",mobile);
                    stringStringHashMap.put("token", token);
                    stringStringHashMap.put("name", edt_name_family.getText().toString().trim());
                    stringStringHashMap.put("invitedMobile", reagent_phone_number.getText().toString().trim());
                    stringStringHashMap.put("gender", gender);

                    vollayRequest vollayRequest = new vollayRequest();
                    vollayRequest.requester(stringStringHashMap, Register.this, "submit", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) throws JSONException {


                            Intent intent = new Intent(Register.this, MainActivity.class);
                            //SAVE TO SHEARED

                            String name = nameFamily;
                            String dimound_count = "0";
//                            gender = gender;

                            sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                            sharedPreferences.edit().putString("name", name).apply();
                            sharedPreferences.edit().putString("dimound_count", dimound_count).apply();
                            sharedPreferences.edit().putString("gender", gender).apply();

                            startActivity(intent);
                            finish();

                        }
                    });


                }
            }
        });

    }

    private void defineObjects() {
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_conformation =(Button) findViewById(R.id.btn_conformation);
        reagent_phone_number=(EditText)findViewById(R.id.reagent_phone_number);
        edt_name_family=(EditText)findViewById(R.id.edt_name_family);
        rbtn_men=(RadioButton) findViewById(R.id.rbtn_men);
        rbtn_women=(RadioButton) findViewById(R.id.rbtn_women);

        sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        mobile = sharedPreferences.getString("mobile", null);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
