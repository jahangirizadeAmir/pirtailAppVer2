package com.pirtail.piratilgame.Gamer.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    EditText edt_phone_number;
    Button btn_conformation;
    String userPhoneNumber;
    CustomToast customToast;
    HashMap<String,String> stringStringHashMap;
    ProgressDialog progressDialog;
    vollayRequest vollay_Request;
    Intent intent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineObjects();

        btn_conformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userPhoneNumber=edt_phone_number.getText().toString().trim();

                if (userPhoneNumber.equals("") || userPhoneNumber.length() < 11 || userPhoneNumber.length() > 11){
                    customToast = new CustomToast(getApplicationContext(), getResources().getString(R.string.incompatibleRegenantPhoneNUmber), CustomToast.info, CustomToast.Bottom);
                    customToast.getToast().show();
                }else {

                    stringStringHashMap=new HashMap<>();
                    stringStringHashMap.put("mobile",userPhoneNumber);
                    progressDialog= new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage(getResources().getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    vollay_Request = new vollayRequest();
                    vollay_Request.requester(stringStringHashMap, MainActivity.this, "submitUser", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) {
                            progressDialog.dismiss();
                            try {
                                intent = new Intent(MainActivity.this, Main2Activity.class);
                                intent.putExtra("mobile",edt_phone_number.getText().toString().trim());
                                intent.putExtra("type",result.getString("type"));
                                startActivity(intent);
                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }

            }
        });

    }

    private void defineObjects() {
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_conformation=(Button)findViewById(R.id.btn_conformation);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
