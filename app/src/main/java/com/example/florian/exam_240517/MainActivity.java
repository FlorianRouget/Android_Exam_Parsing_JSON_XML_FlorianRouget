package com.example.florian.exam_240517;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BTN_JSON;
    Button BTN_XML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTN_JSON = (Button) findViewById(R.id.btn_JSON);
        BTN_XML = (Button) findViewById(R.id.btn_XML);

        BTN_JSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JSON_Parsing.class);
                startActivity(intent);
            }
        });

        BTN_XML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XML_Parsing.class);
                startActivity(intent);
            }
        });

    }
}
