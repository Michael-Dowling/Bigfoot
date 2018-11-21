package com.bigfoot.bigfoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Button button = (Button) findViewById(R.id.nextButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //original code
                Intent i = new Intent(InstructionActivity.this, MainActivity.class);

                //modded for testing database switch these two lines
                //Intent i = new Intent(InstructionActivity.this, TestDbActivity.class);

                startActivity(i);
                finish();
            }
        });
    }


}
