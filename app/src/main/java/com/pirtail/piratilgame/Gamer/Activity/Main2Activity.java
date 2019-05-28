package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.R;

public class Main2Activity extends AppCompatActivity {

    Button btn_send;
    EditText edt_conformation_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        defineObjects();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void defineObjects() {
        btn_send=(Button)findViewById(R.id.btn_send);
        edt_conformation_code=(EditText) findViewById(R.id.edt_conformation_code);
    }
}
