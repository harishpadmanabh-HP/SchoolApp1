package com.schoolmanapp.ktct_school.school.parent.Java_class;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.schoolmanapp.ktct_school.school.R;

import java.util.Calendar;

public class WakeUp extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    MediaPlayer mp;
    Context context = this;
    EditText hr,min;
    Switch set;
    int hour,minute;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        hr=findViewById(R.id.wakeuphour);
        min=findViewById(R.id.wakeupminute);
        set=findViewById(R.id.switchset);
        set.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if(!hr.getText().toString().equals("")||!min.getText().toString().equals("")) {
                        hour = Integer.parseInt(hr.getText().toString());
                        minute = Integer.parseInt(min.getText().toString());
                        setAlarm();
                        Toast.makeText(context, "Wake up call set up at "+hour+" : "+minute, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WakeUp.this,student_activity.class));
                    }else{
                        set.setChecked(false);
                        Toast.makeText(context, "Enter time ", Toast.LENGTH_SHORT).show();
                    }

                }else if(!isChecked){
                    if (mediaPlayer.isPlaying())
                    {
                        mediaPlayer.stop();
                        finish();
                    }
                }


            }
        });


        if (getIntent().getBooleanExtra("lock", false)) {
            // These next few lines of code open a window with the MainActivity
            // evan if the device is locked
            Window win = this.getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            win.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

            //coming from wake lock  alarm recieved.

            //set.setChecked(true);
            mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
            mediaPlayer.start();
            mediaPlayer.getDuration();

            //startActivity(new Intent(MainActivity.this,POPUP.class));

            set.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!isChecked)
                    {
                        if (mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                            finish();
                        }
                    }

                }
            });
        }

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    }
    public void setAlarm() {
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 10000, pendingIntent);


        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);


// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);



    }
    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            finish();
        }


    }

}
