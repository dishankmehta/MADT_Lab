package com.example.dishank.madt_lab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Dishank on 4/13/2016.
 */
public class InternalStorage extends AppCompatActivity {

    EditText editText;
    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        editText = (EditText) findViewById(R.id.etmsg);
        textView= (TextView) findViewById(R.id.txtdisplay);
        textView.setVisibility(View.GONE);
    }

    public void writeMessage(View view){
        String message=editText.getText().toString();
        String filename="hello_file";
        try {
            FileOutputStream outputStream=openFileOutput(filename,MODE_PRIVATE);
            outputStream.write(message.getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), "Message Saved", Toast.LENGTH_SHORT).show();
            editText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readMessage(View view){
        //textView.setText("");
        String filename="hello_file";
        String message;
        try {
            FileInputStream inputStream=openFileInput(filename);
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            while ((message=bufferedReader.readLine())!=null){
                stringBuffer.append(message + "\n");
            }
            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
