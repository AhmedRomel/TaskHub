package com.example.saleem.testgithub.reminder;


/**
 * Created by ABU-JAMAL on 15/03/2016.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import com.example.saleem.testgithub.R;
import com.example.saleem.testgithub.activity.MainActivity;

import java.io.IOException;
import java.util.Random;

public class RingtonePlayingService extends Service {

    private boolean isRunning;
    private Context context;
    MediaPlayer mMediaPlayer;
    private int startId;

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("MyActivity", "In the Sound  service");
        return null;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {


        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("An Alram is going off" + "!")
                .setContentText("Click me!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();
        mNM.notify(0, mNotify);

        String state = intent.getExtras().getString("extra");

        Log.e("what is going on here  ", state);

        assert state != null;
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }


        String sound_id = intent.getExtras().getString("quote id");
        Log.e("Service: Sound id is " , sound_id);

        if(!this.isRunning && startId == 1) {
            Log.e("if there was not sound ", " and you want start");


            this.isRunning = true;
            this.startId = 1;


            assert sound_id != null;
            if (sound_id.equals("0")) {

                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));

                if (random_number == 1) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_1);
                }
                else if (random_number == 2) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_2);
                }
                else if (random_number == 3) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_3);
                }
                else if (random_number == 4) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_4);
                }
                else if (random_number == 5) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_5);
                }
                else if (random_number == 6) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_6);
                }
                else if (random_number == 7) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_7);
                }
                else if (random_number == 8) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_8);
                }
                else if (random_number == 9) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_9);
                }
                else {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_1);
                }
            }
            else if (sound_id.equals("1")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_1);
            }
            else if (sound_id.equals("2")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_2);
            }
            else if (sound_id.equals("3")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_3);
            }
            else if (sound_id.equals("4")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_4);
            }
            else if (sound_id.equals("5")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_5);
            }
            else if (sound_id.equals("6")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_6);
            }
            else if (sound_id.equals("7")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_7);
            }
            else if (sound_id.equals("8")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_8);
            }
            else if (sound_id.equals("9")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_9);
            }
            else {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_1);
            }

            mMediaPlayer.start();

        }
        else if (!this.isRunning && startId == 0){
            Log.e("if there was not sound ", " and you want end");

            this.isRunning = false;
            this.startId = 0;

        }

        else if (this.isRunning && startId == 1){
            Log.e("if there is sound ", " and you want start");

        }
        else {
            Log.e("if there is sound ", " and you want end");

            mMediaPlayer.stop();
            mMediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }


        Log.e("MyActivity", "In the service");

        return START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        this.isRunning = false;
    }


}