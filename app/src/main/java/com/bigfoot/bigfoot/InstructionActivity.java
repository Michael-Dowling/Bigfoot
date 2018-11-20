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
        TextView instr1 = (TextView) findViewById(R.id.inst1);
        TextView instr2 = (TextView) findViewById(R.id.inst2);
        TextView instr3 = (TextView) findViewById(R.id.inst3);
        TextView instr4 = (TextView) findViewById(R.id.inst4);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InstructionActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


}
