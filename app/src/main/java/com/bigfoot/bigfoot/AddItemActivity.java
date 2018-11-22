package com.bigfoot.bigfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddItemActivity extends AppCompatActivity {
    public TextView barcodeNumber;
    public EditText itemName;
    public EditText recType;
    public EditText binClr;
    public EditText description;
    protected void insertToDatabase(String name, String type, String Clr, String dscrptn){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        barcodeNumber = findViewById(R.id.barcodeNumber);
        itemName = (EditText)findViewById(R.id.itemName);
        recType = (EditText)findViewById(R.id.recType);
        binClr = (EditText)findViewById(R.id.BinColour);
        String iName;
        String rType;
        String bClr;
        String dtion;

        Button btn = findViewById(R.id.submitbtn);
        iName = itemName.getText().toString();
        rType = recType.getText().toString();
        bClr = binClr.getText().toString();
        dtion = description.getText().toString();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDatabase(iName,rType,bClr,dtion);
            }
        });
    }
}
