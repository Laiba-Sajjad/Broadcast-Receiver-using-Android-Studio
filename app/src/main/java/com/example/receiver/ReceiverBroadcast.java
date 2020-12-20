package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ReceiverBroadcast extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.sender.ACTION_SEND".equals(intent.getAction()));
        {
            String extra = intent.getStringExtra("com.example.sender.EXTRA");
            Toast.makeText(context,"Receiver: "+extra,Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = context.getSharedPreferences("myprefs",Context.MODE_PRIVATE).edit();
            editor.putString("Message:",extra);
            //editor.commit();
            editor.apply();
        }
    }
}
