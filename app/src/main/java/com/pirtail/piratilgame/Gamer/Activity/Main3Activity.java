package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Main3Activity extends AppCompatActivity {

    EditText edt_username, edt_phone_number, edt_name_family, reagent_phone_number;
    RadioButton rbtn_men, rbtn_women;
    Button btn_send;
    String username, regenantPhoneNumber,reagent , nameFamily, gender, token, mobile;
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
        setContentView(R.layout.activity_main3);

        defineObjects();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //sending data to server process
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    stringRequest = new StringRequest(
                            Request.Method.POST,
                            "http://piratil.com/game/request/complateSubmit.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        jsonObject=new JSONObject(response);

                                        //app version ERROR
                                        if (!jsonObject.getBoolean("version")) {
                                            builder = new AlertDialog.Builder(Main3Activity.this);
                                            builder.setTitle("خطایی پیش آمده");
                                            builder.setMessage("نسخه جدید را دانلود کنید");
                                            builder.setCancelable(false);
                                            builder.show();
                                        }else {

                                            //Server error message
                                            if (jsonObject.getBoolean("error")){
                                                customToast = new CustomToast(getApplicationContext(), jsonObject.getString(getResources().getString(R.string.MSG_ERROR)), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Bottom);
                                                customToast.getToast().show();
                                            }else {
                                                //Required server information from the user
                                                sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                                                sharedPreferences.edit().putBoolean("login", true).apply();
                                                sharedPreferences.edit().putString("token", token).apply();
                                                sharedPreferences.edit().putString("mobile", mobile).apply();
                                                intent = new Intent(Main3Activity.this, Main4Activity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
                    ){

                        //Required server information from the user
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            stringStringHashMap = new HashMap<>();
                            stringStringHashMap.put("appVersion","1");
                            stringStringHashMap.put("device","android");
                            stringStringHashMap.put("mobile",mobile);
                            stringStringHashMap.put("token", token+getSaltString());
                            stringStringHashMap.put("name", edt_name_family.getText().toString().trim());
                            stringStringHashMap.put("invitedMobile", reagent_phone_number.getText().toString().trim());
                            stringStringHashMap.put("gender", gender);
                            return  stringStringHashMap;
                        }


                    };

                    requestQueue.add(stringRequest);

                }
            }
        });

    }

    private void defineObjects() {
        edt_username=(EditText)findViewById(R.id.edt_username);
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_send=(Button) findViewById(R.id.btn_conformation);
        reagent_phone_number=(EditText)findViewById(R.id.reagent_phone_number);
        edt_name_family=(EditText)findViewById(R.id.edt_name_family);
        rbtn_men=(RadioButton) findViewById(R.id.rbtn_men);
        rbtn_women=(RadioButton) findViewById(R.id.rbtn_women);

        username = edt_username.getText().toString().trim();
        regenantPhoneNumber = reagent_phone_number.getText().toString().trim();
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

    //encrypting token
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
