package com.example.dishank.madt_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainBroadcast extends AppCompatActivity {

    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast);
        et = (EditText) findViewById(R.id.editText25);

    }
    public void send(View view){
        Intent i = new Intent("action");
        i.putExtra("flag",et.getText().toString());
        sendBroadcast(i);
    }
}
