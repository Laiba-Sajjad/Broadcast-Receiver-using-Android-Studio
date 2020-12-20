package com.example.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ForegroundService extends AppCompatActivity {

    static TextView txt_progressBar;
    static ProgressBar progressBar;

    Button service ,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        service = (Button)findViewById(R.id.start_services);
        back = (Button)findViewById(R.id.foreground_back);

        txt_progressBar = (TextView)findViewById(R.id.progressbar_txt);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        txt_progressBar.setVisibility(View.INVISIBLE);

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_progressBar.setText("Downloading...0%");
                progressBar.setVisibility(View.VISIBLE);
                txt_progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                Intent intent = new Intent(ForegroundService.this,MyService.class);
                startService(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForegroundService.this,MainActivity.class));
            }
        });
    }

    static void display(String text) {
        if(text.equals("100")|| text.equals("Download complete")) {
            txt_progressBar.setText("Downloading Complete");
            progressBar.setProgress(0);
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            txt_progressBar.setText("Downloading... " + text + "%");
        }
    }
}
