package com.pirtail.piratilgame.Gamer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.pirtail.piratilgame.R;

public class Main2Activity extends AppCompatActivity {

    EditText edt_username, edt_phone_number, edt_name_family;
    RadioButton rbtn_men, rbtn_women;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        defineObjects();



    }

    private void defineObjects() {
        edt_username=(EditText)findViewById(R.id.edt_phone_number);
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        edt_name_family=(EditText)findViewById(R.id.edt_name_family);
        rbtn_men=(RadioButton) findViewById(R.id.rbtn_men);
        rbtn_women=(RadioButton) findViewById(R.id.rbtn_women);
    }
}
