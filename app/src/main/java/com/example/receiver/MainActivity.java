package com.example.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ReceiverBroadcast objBroadcast = new ReceiverBroadcast();
    Button display_msg, foreground_service, save_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////Broadcast Receiver
        IntentFilter intentFilter = new IntentFilter("com.example.sender.ACTION_SEND");
        registerReceiver(objBroadcast,intentFilter);

        ////Display Messages
        display_msg = (Button)findViewById(R.id.btn_display);
        display_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,DisplayMsg.class));
            }
        });

        ////Foreground Service
       foreground_service = (Button)findViewById(R.id.btn_foreground);
        /*Intent i = new Intent(context,MyService.class);
        i.putExtra("KEY1","Value to be used by service");
        startService(i);*/
        foreground_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForegroundService.class));
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(objBroadcast);
    }
}
