package com.example.dishank.madt_lab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dishank on 3/4/2016.
 */
public class Tab1Fragment extends Fragment implements View.OnClickListener{

    EditText ename;
    EditText ematch,egoals;
    Button badd;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1fragment,container,false);
        ename = (EditText) rootView.findViewById(R.id.addname);
        ematch = (EditText) rootView.findViewById(R.id.addmatch);
        egoals = (EditText) rootView.findViewById(R.id.addgoals);
        badd = (Button) rootView.findViewById(R.id.addbut);
        badd.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String t = ename.getText().toString();
        if(t.equals("")||t==null||t.length()==0)
        {
            Toast.makeText(getActivity(), "Re Enter", Toast.LENGTH_LONG).show();
        }
        else
        {
            DBHelper addb1 = new DBHelper(getContext());
            addb1.open();
            addb1.insertEntry(ename.getText().toString(), ematch.getText().toString(), egoals.getText().toString());
            Toast.makeText(getActivity(), "Data Entered", Toast.LENGTH_LONG).show();
            addb1.close();

        }
    }
}
