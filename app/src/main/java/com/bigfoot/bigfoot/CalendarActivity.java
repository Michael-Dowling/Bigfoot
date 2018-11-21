package com.bigfoot.bigfoot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.sundeepk.compactcalendarview.domain.Event;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        //back button, worst case we can go
        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(CalendarActivity.this, ScanActivity.class);
                    startActivity(i);
                }
            });
        createNotificationChannel();
        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callNotification("Garbage Pikcup Tommorow","Blue Bin");
            }
        });

        TextView text_view_2 = findViewById(R.id.text_view_2);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        Event evGarbage;
        for(int i = 0; i < 4; i++)
        {
            evGarbage = new Event(Color.BLACK,1541480400000L+(i*604800010));
            compactCalendarView.addEvent(evGarbage);
            if(i%2 == 0)
            {
                evGarbage = new Event(Color.GRAY,1541480400000L+(i*604800010));
                compactCalendarView.addEvent(evGarbage);
            }
            else
            {
                evGarbage = new Event(Color.BLUE,1541480400000L+(i*604800010));
                compactCalendarView.addEvent(evGarbage);
            }
        }

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                if(!events.isEmpty()) {
                    long timeString = events.get(0).getTimeInMillis() - 1541480400000L;
                    if(timeString%1209600020 == 0) {
                        callNotification("Garbage Pick Tommorow", "Black and Gray Bin");
                    }
                    else {
                        callNotification("Garbage Pick Tommorow", "Black and Blue Bin");
                    }
                    }
                }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d("MainActivity", "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case (R.id.action_schedule):
                Intent i = new Intent(this, CalendarActivity.class);
                startActivity(i);
                return true;
            case R.id.action_facts:
                return true;
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("channel1", "Channel 1",NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public void callNotification(String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel1") //CHANNEL_ID
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_ALL);
        //.setPriority(Notification.PRIORITY_HIGH);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
