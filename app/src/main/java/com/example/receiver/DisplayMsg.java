package com.example.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DisplayMsg extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    String mypreference = "myprefs";
    String Name = "Message:";
    Button display_msg, back;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_msg);
        display_msg = (Button) findViewById(R.id.display_act_msg);
        back = (Button)findViewById(R.id.display_back);
        txt = (TextView)findViewById(R.id.display_txt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayMsg.this,MainActivity.class));
            }
        });
        display_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // // String msg= pref.getString("Message: ",objBroadcast.msg);
                ////Toast.makeText(MainActivity.this,""+msg,Toast.LENGTH_SHORT).show();

                // startActivity(new Intent(DisplayMsg.this,ListDataActivity.class));

                //SharedPreferences sharedPref = DisplayMsg.this.getPreferences(Context.MODE_PRIVATE);

                sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);

                if(sharedpreferences.contains(Name)){
                    txt.setText(sharedpreferences.getString(Name,""));
                }
                Toast.makeText(DisplayMsg.this,"Message Displayed",Toast.LENGTH_SHORT).show();


            }
        });
    }
}
