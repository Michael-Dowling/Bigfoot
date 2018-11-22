package com.bigfoot.bigfoot;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    static{
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //temporary testing
        Intent enterRecyling = new Intent(MainActivity.this,FirstOpenActivity.class);
        startActivity(enterRecyling);



        //done testing

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);


        //more testing below:
        /*SharedPreferences prefs = getSharedPreferences("Prefs", MODE_PRIVATE);
        String recycleDay = prefs.getString("recycle_day", null);
        btn.setText(recycleDay); */
        // Button calendarBtn = findViewById(R.id.calendarBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.button);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)findViewById((R.id.autoCompleteTextView));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CALL C++ CODE TO GET STRING HERE, send it to results
                String inputText = autoCompleteTextView.getText().toString();
                //string array
                ScanActivity.getFromSearch(inputText);
                 Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                 startActivity(intent);
            }
        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            switch (item.getItemId()) {
                case (R.id.action_schedule):
                    Intent i = new Intent(this, CalendarActivity.class);
                    startActivity(i);
                    return true;
                case R.id.action_facts:
                    return true;
                case R.id.action_previous:
                    Intent j = new Intent(this, ResultsActivity.class);
                    startActivity(j);
            }

            //noinspection SimplifiableIfStatement


            return super.onOptionsItemSelected(item);
        }
    //call the get_from name
    public native String getBinTypeFromName(String name);



    }
