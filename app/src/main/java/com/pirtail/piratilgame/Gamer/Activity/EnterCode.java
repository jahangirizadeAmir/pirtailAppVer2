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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterCode extends AppCompatActivity {

    Button btn_send;
    EditText edt_conformation_code;
    TextView txt_counter, txt_resend_code;
    String conformationCode, mobile, type;
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
        setContentView(R.layout.code_enter);

        defineObjects();

        //  get the static data from PhoneEnter
        requestQueue = Volley.newRequestQueue(EnterCode.this);
        intent=getIntent();
        submit=intent.getBooleanExtra("submit", true);
        dataComplete=intent.getBooleanExtra("dataComplete", false);
        mobile=intent.getStringExtra("mobile");
        type=intent.getStringExtra("type");

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                conformationCode= edt_conformation_code.getText().toString().trim();

                //Empty conformation code ERROR
                if (conformationCode.equals("")){
                    customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.emptyConformationCodeERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                    customToast.getToast().show();
                }else {
                    //Required server information from the user
                    stringStringHashMap= new HashMap<>();
                    stringStringHashMap.put("mobile", mobile);
                    stringStringHashMap.put("type", type);
                    stringStringHashMap.put("code", conformationCode);

                    //Server error message
                    progressDialog = new ProgressDialog(EnterCode.this);
                    progressDialog.setMessage(getResources().getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    vollay_Request= new vollayRequest();
                    vollay_Request.requester(stringStringHashMap, EnterCode.this, "checkCode.php", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) throws JSONException {
                            progressDialog.dismiss();
                            if (result.getBoolean("error")){
                                customToast=new CustomToast(getApplicationContext(), getResources().getString(R.string.MSG_ERROR), com.pirtail.piratilgame.Class.CustomToast.danger, com.pirtail.piratilgame.Class.CustomToast.Top);
                                customToast.getToast().show();
                            }else {
                                sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
                                sharedPreferences.edit().putString("mobile",mobile).apply();
                                sharedPreferences.edit().putString("token",result.getString("token")).apply();
                                intent = new Intent(EnterCode.this, Register.class);
                                startActivity(intent);
                                finish();

                                txt_counter.setEnabled(false);
                            }
                        }
                    });

                }

            }
        });

        countDownTimer = new CountDownTimer(60000, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                txt_counter.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                txt_resend_code.setTextColor(getResources().getColor(R.color.link_color_enable));
                txt_resend_code.setEnabled(true);

                txt_resend_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countDownTimer.start();
                        txt_resend_code.setTextColor(getResources().getColor(R.color.link_color_disable));
                        final ProgressDialog progressDialog = new ProgressDialog(EnterCode.this);
                        progressDialog.setMessage(getResources().getString(R.string.please_wait));
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        stringRequest = new StringRequest(
                                Request.Method.POST,
                                "http://piratil.com/game/request/submitUser.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        progressDialog.dismiss();

                                        try {
                                            jsonObject = new JSONObject(response);
                                            if (!jsonObject.getBoolean("version")) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(EnterCode.this);
                                                builder.setTitle("خطایی پیش آمده");
                                                builder.setMessage("نسخه جدید را دانلود کنید");
                                                builder.setCancelable(false);
                                                builder.show();
                                            } else {

                                                customToast=new CustomToast(
                                                        getApplicationContext(),
                                                        getResources().getString(R.string.resendingConformationCodeSuccess),
                                                        com.pirtail.piratilgame.Class.CustomToast.danger,
                                                        com.pirtail.piratilgame.Class.CustomToast.Top
                                                );
                                                customToast.getToast().show();

                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
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

                    }
                });

            }
        };
        countDownTimer.start();

    }

    private void defineObjects() {
        btn_send=(Button)findViewById(R.id.btn_send);
        edt_conformation_code=(EditText) findViewById(R.id.edt_conformation_code);
        txt_counter=(TextView) findViewById(R.id.txt_counter);
        txt_resend_code=(TextView) findViewById(R.id.txt_resend_code);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
