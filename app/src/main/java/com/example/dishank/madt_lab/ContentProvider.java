package com.example.dishank.madt_lab;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

/**
 * Created by Dishank on 4/11/2016.
 */
public class ContentProvider extends AppCompatActivity implements View.OnClickListener {


    String name,goal;
    EditText playername,playergoal;
    Button bshow, bsubmit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ContentResolver cr;
    String def = "";
    String color = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentview);


        playername = (EditText) findViewById(R.id.pname);
        playergoal = (EditText) findViewById(R.id.pgoal);

        bshow = (Button) findViewById(R.id.pshowbutton);
        bsubmit = (Button) findViewById(R.id.pbutton);

        bshow.setOnClickListener(this);
        bsubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pbutton:
                ContentValues contentValues = new ContentValues();
                cr = getApplicationContext().getContentResolver();
                name = playername.getText().toString();
                goal = playergoal.getText().toString();

                contentValues.put(ProviderFile.PLAYERNAME, name);
                contentValues.put(ProviderFile.GOALS, goal);

                Uri id1 = cr.insert(ProviderFile.CONTENT_URI, contentValues);

                playername.setText("");
                playergoal.setText("");

                Toast.makeText(this, "Data Inserted..!!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.pshowbutton:
                Intent i = new Intent(ContentProvider.this, ShowContent.class);
                startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

