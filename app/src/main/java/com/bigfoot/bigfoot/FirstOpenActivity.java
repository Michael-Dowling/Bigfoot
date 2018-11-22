package com.bigfoot.bigfoot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Calendar;

public class FirstOpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_open);
        //set up dropdown list for recycling day
        Spinner recDaySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weekdays,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recDaySpinner.setAdapter(adapter);
        //set spinner listener
        recDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  final String[] s = getResources().getStringArray(R.array.weekdays);
                  SharedPreferences.Editor editor = getSharedPreferences("Prefs",MODE_PRIVATE).edit();
                  editor.putString("recycle_day",s[i]);
              }

              @Override
              public void onNothingSelected(AdapterView<?> adapterView) {
              }
          }
        );

        Spinner recTypeSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.recycling_types,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recTypeSpinner.setAdapter(adapter1);
        //set recycle type spinner listener
        recTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String[] s = getResources().getStringArray(R.array.recycling_types);
                SharedPreferences.Editor editor = getSharedPreferences("Prefs",MODE_PRIVATE).edit();
                editor.putString("first_recyle_type",s[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button done = (Button) findViewById(R.id.button3);
        done.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Spinner recDaySpinner = (Spinner) findViewById(R.id.spinner);
                int dayNextRec = 2 + recDaySpinner.getSelectedItemPosition();
                //get day of next first recyling (for blue/grey bin reference)
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int dayDiff = dayNextRec-day;
                int nextRecylingDate;
                if(dayDiff < 0){
                    nextRecylingDate = date + 7 + dayDiff;
                }else{
                    nextRecylingDate = date + dayDiff;
                }
                SharedPreferences.Editor editor = getSharedPreferences("Prefs",MODE_PRIVATE).edit();
                editor.putInt("first_recycling_date",nextRecylingDate);

                Intent i = new Intent(FirstOpenActivity.this,InstructionActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


}
