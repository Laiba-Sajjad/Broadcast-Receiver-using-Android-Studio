package com.example.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    int counter = 0;
    public MyService() {
    }
    ForegroundService foregroundServiceObj = new ForegroundService();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
   public int onStartCommand(Intent intent, int flags, int startId) {

       Thread thread=new Thread(new Runnable() {
           @Override
           public void run() {
               Task task=new Task();
               task.execute();
               stopForeground(true);
           }
       });
       thread.start();
        return START_STICKY;
    }
    private class Task extends AsyncTask<Void, Integer, String> {

        protected  String doInBackground(Void...voids) {
            final Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run()
                {
                    counter++;
                    ForegroundService.progressBar.setProgress(counter);

                    if(counter == 100)
                        t.cancel();
                }
            };
            t.schedule(tt,0,100);
          //  for (int i = 1; i < 11; i++) {
            int j=0, i;
            synchronized (this) {
                while (j < 100) {
                    try {
                        // Thread.sleep(100);
                        wait(1000);
                        i=j+10;
                        j=j+10;
                        publishProgress(j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            onPostExecute(100);
            return "Download Completed!";
        }


        protected void onProgressUpdate(Integer... values) {


           ForegroundService.display(String.valueOf(values[0]));
        }
        protected void onPostExecute(Integer result) {
            ForegroundService.display("Download complete");
        }
    }

}
