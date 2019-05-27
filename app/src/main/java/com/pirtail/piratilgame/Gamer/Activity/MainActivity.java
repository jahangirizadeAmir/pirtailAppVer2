package com.pirtail.piratilgame.Gamer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.pirtail.piratilgame.R;

public class MainActivity extends AppCompatActivity {


    EditText edt_phone_number;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineObjects();



    }

    private void defineObjects() {
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_send=(Button)findViewById(R.id.btn_send);
    }
}
