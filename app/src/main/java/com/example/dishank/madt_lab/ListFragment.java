package com.example.dishank.madt_lab;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener {



    Button bclick1,bclick2,bclick3,bclick4;
    RelativeLayout rl;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment,container,false);

        rl = (RelativeLayout) rootView.findViewById(R.id.rlayout);

        bclick1 = (Button) rootView.findViewById(R.id.b1);
        bclick2 = (Button) rootView.findViewById(R.id.b2);
        bclick3 = (Button) rootView.findViewById(R.id.b3);
        bclick4 = (Button) rootView.findViewById(R.id.b4);

        bclick1.setOnClickListener(this);
        bclick2.setOnClickListener(this);
        bclick3.setOnClickListener(this);
        bclick4.setOnClickListener(this);



        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b1:
                Intent i = new Intent(getActivity(),FinalActivity.class);
                startActivity(i);
                break;

            case R.id.b2:
                Intent i1 = new Intent(getActivity(), ContentProvider.class);
                startActivity(i1);
                break;

            case R.id.b3:
                Intent i2 = new Intent(getActivity(), InternalStorage.class);
                startActivity(i2);
                break;

            case R.id.b4:
                Intent i3 = new Intent(getActivity(), MainBroadcast.class);
                startActivity(i3);
                break;
        }
    }


}
