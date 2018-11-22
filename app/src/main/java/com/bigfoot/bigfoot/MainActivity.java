package com.bigfoot.bigfoot;

import android.app.SearchManager;
import android.content.Context;
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

    public static TextView tvresult;

    static{
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvresult = findViewById(R.id.tvresult);

        Button btn = findViewById(R.id.btn);
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
                //CALL C++ CODE TO GET STRING HERE
                String inputText = autoCompleteTextView.getText().toString();
                tvresult.setText(getBinTypeFromName(inputText));
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

    public native String getBinTypeFromName(String name);

    }
