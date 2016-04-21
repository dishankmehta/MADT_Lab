package com.example.dishank.madt_lab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Dishank on 4/13/2016.
 */
public class customBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getExtras().getString("flag");
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
