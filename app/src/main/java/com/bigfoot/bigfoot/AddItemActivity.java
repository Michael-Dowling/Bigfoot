package com.bigfoot.bigfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.bigfoot.bigfoot.ScanActivity.BARCODE_MESSAGE;
import static com.bigfoot.bigfoot.ScanActivity.RESULTS_MESSAGE;

public class AddItemActivity extends AppCompatActivity {
    public TextView barcodeNumber;
    public EditText itemName;
    public EditText recType;
    public EditText binClr;
    public EditText description;
    private String iName;
    private String rType;
    private String bClr;
    private String dtion;
    protected void insertToDatabase(String name, String type, String Clr, String dscrptn){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();
        long resultsFromScan[] = intent.getLongArrayExtra(BARCODE_MESSAGE);
        long barcode = resultsFromScan[0];

        barcodeNumber = findViewById(R.id.barcodeNumber);
        itemName = (EditText)findViewById(R.id.itemName);
        recType = (EditText)findViewById(R.id.recType);
        binClr = (EditText)findViewById(R.id.BinColour);
        Button btn = findViewById(R.id.submitbtn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iName = itemName.getText().toString();
                rType = recType.getText().toString();
                bClr = binClr.getText().toString();
                dtion = description.getText().toString();
                insertToDatabase(iName,rType,bClr,dtion);
            }
        });
    }
}
