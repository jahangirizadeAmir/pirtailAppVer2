package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void defineObjects() {
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        btn_send=(Button)findViewById(R.id.btn_send);
    }
}
