package com.bigfoot.bigfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddItemActivity extends AppCompatActivity {
    public TextView barcodeNumber;
    public TextView itemName;
    public TextView recyclingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Button btn = findViewById(R.id.btn);
        // Button calendarBtn = findViewById(R.id.calendarBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
