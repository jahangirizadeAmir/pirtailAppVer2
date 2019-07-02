package com.pirtail.piratilgame.Gamer.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PhoneEnterActivity extends AppCompatActivity {

    @BindView(R.id.edt_phone_number)
    EditText getEdt_phone_number;

    String userPhoneNumber;
    CustomToast customToast;
    HashMap<String, String> stringStringHashMap;
    ProgressDialog progressDialog;
    vollayRequest vollay_Request;
    Intent intent;
    @BindView(R.id.btn_conformation)
    Button btnConformation;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_enter);
        ButterKnife.bind(this);

        defineObjects();



    }

    private void defineObjects() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btn_conformation)
    public void onViewClicked() {
        userPhoneNumber= getEdt_phone_number.getText().toString().trim();

        if (userPhoneNumber.equals("") || userPhoneNumber.length() < 11 || userPhoneNumber.length() > 11){
            customToast = new CustomToast(getApplicationContext(), getResources().getString(R.string.incompatibleRegenantPhoneNUmberERROR), CustomToast.info, CustomToast.Bottom);
            customToast.getToast().show();
        }else {

            stringStringHashMap=new HashMap<>();
            stringStringHashMap.put("mobile",userPhoneNumber);
            progressDialog= new ProgressDialog(PhoneEnterActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.please_wait));
            progressDialog.setCancelable(false);
            progressDialog.show();

            vollay_Request = new vollayRequest();
            vollay_Request.requester(stringStringHashMap, PhoneEnterActivity.this, "submitUser", new ServerCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    progressDialog.dismiss();
                    try {
                        intent = new Intent(PhoneEnterActivity.this, EnterCodeActivity.class);
                        intent.putExtra("mobile", getEdt_phone_number.getText().toString().trim());
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
}
