package com.example.dishank.madt_lab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Dishank on 4/13/2016.
 */
public class ContentUpdate extends AppCompatActivity implements View.OnClickListener {

    Button bupdate,bdelete;
    EditText uname, ugoal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        bupdate = (Button) findViewById(R.id.updatebutton);
        bdelete = (Button) findViewById(R.id.deletebutton);

        uname = (EditText) findViewById(R.id.updatename);
        ugoal = (EditText) findViewById(R.id.updategoal);

        bupdate.setOnClickListener(this);
        bdelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
