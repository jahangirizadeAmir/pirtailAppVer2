package com.pirtail.piratilgame.Gamer.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterCodeActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_send)
            Button getBtn_send;

    @BindView(R.id.edt_conformation_code)
            EditText getEdt_conformation_code;

    @BindView(R.id.txt_counter)
            TextView getTxt_counter;

    @BindView(R.id.txt_resend_code)
            TextView getTxt_resend_code;

    String conformationCode, mobile, token;
    Intent intent;
    RequestQueue requestQueue;
    Boolean submit, dataComplete;
    CustomToast customToast;
    HashMap<String, String> stringStringHashMap;
    ProgressDialog progressDialog;
    vollayRequest vollay_Request;
    SharedPreferences sharedPreferences;
    CountDownTimer countDownTimer;
    StringRequest stringRequest;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_enter);
        ButterKnife.bind(this);

        defineObjects();

        //  get the static data from PhoneEnterActivity
        requestQueue = Volley.newRequestQueue(EnterCodeActivity.this);
        intent=getIntent();
        mobile=intent.getStringExtra("mobile");

        countDownTimer = new CountDownTimer(60000, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                getTxt_counter.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                getTxt_resend_code.setTextColor(getResources().getColor(R.color.link_color_enable));
                getTxt_resend_code.setEnabled(true);

                getEdt_conformation_code.setOnClickListener(EnterCodeActivity.this);

            }
        };
        countDownTimer.start();

    }

    private void defineObjects() {
        getBtn_send.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:

                conformationCode= getEdt_conformation_code.getText().toString().trim();

                //Empty conformation code ERROR
                if (conformationCode.equals("")){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyConformationCodeERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }else {
                    //Required server information from the user
                    stringStringHashMap= new HashMap<>();
                    stringStringHashMap.put("mobile", mobile);
                    stringStringHashMap.put("code", conformationCode);

                    //Server error message
                    progressDialog = new ProgressDialog(EnterCodeActivity.this);
                    progressDialog.setMessage(getResources().getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    vollay_Request= new vollayRequest();
                    vollay_Request.requester(stringStringHashMap, EnterCodeActivity.this, "checkCode", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) throws JSONException {
                            progressDialog.dismiss();
                            if (result.getBoolean("error")){
                                customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.MSG_ERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                                customToast.getToast().show();
                            }else {

                                token = result.getString("token");
                                token= MD5.getMd5(token)+getSaltString();

                                sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
                                sharedPreferences.edit().putString("mobile",mobile).apply();
                                sharedPreferences.edit().putString("token",token).apply();

                                if(result.getBoolean("goToSubmit")){
                                    intent = new Intent(EnterCodeActivity.this, Register.class);
                                    startActivity(intent);
                                    finish();
                                }else{

                                    String name = result.getString("name");
                                    String dimound_count = result.getString("countGem");
                                    String gender = result.getString("gender");

                                    sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                                    sharedPreferences.edit().putString("name", name).apply();
                                    sharedPreferences.edit().putString("dimound_count", dimound_count).apply();
                                    sharedPreferences.edit().putString("gender", gender).apply();

                                    intent = new Intent(EnterCodeActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                getTxt_counter.setEnabled(false);
                            }
                        }
                    });

                }

                break;
            case R.id.edt_conformation_code:

                countDownTimer.start();
                getTxt_resend_code.setTextColor(getResources().getColor(R.color.link_color_disable));
                final ProgressDialog progressDialog = new ProgressDialog(EnterCodeActivity.this);
                progressDialog.setMessage(getResources().getString(R.string.please_wait));
                progressDialog.setCancelable(false);
                progressDialog.show();

                stringRequest = new StringRequest(
                        Request.Method.POST,
                        "http://piratil.com/game/request/submitUser",
                        response -> {

                            progressDialog.dismiss();

                            try {
                                jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("version")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(EnterCodeActivity.this);
                                    builder.setTitle("خطایی پیش آمده");
                                    builder.setMessage("نسخه جدید را دانلود کنید");
                                    builder.setCancelable(false);
                                    builder.show();



                                } else {

                                    customToast=new CustomToast(
                                            getApplicationContext(),
                                            getResources().getString(R.string.resendingConformationCodeSuccess),
                                            CustomToast.danger,
                                            CustomToast.Top
                                    );
                                    customToast.getToast().show();

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        },
                        error -> {

                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> stringStringHashMap = new HashMap<>();
                        stringStringHashMap.put("appVersion", "1");
                        stringStringHashMap.put("device", "android");
                        stringStringHashMap.put("mobile", mobile);
                        return stringStringHashMap;
                    }
                };
                requestQueue.add(stringRequest);

                break;
            case R.id.txt_counter:

                break;
            case R.id.txt_resend_code:

                break;
        }
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

    // Java program to calculate MD5 hash value
    public static class MD5 {
        public static String getMd5(String input)
        {
            try {

                // Static getInstance method is called with hashing MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // digest() method is called to calculate message digest
                //  of an input digest() return array of byte
                byte[] messageDigest = md.digest(input.getBytes());

                // Convert byte array into signum representation
                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }

            // For specifying wrong message digest algorithms
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        // Driver code
        public static void main(String args[]) throws NoSuchAlgorithmException
        {
            String s = "GeeksForGeeks";
            System.out.println("Your HashCode Generated by MD5 is: " + getMd5(s));

        }
    }

}
