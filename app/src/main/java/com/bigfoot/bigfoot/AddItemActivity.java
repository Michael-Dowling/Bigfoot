package com.bigfoot.bigfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
    private long barcode;

    protected void insertToDatabase(String name, String type, String Clr, String dscrptn){
        String albertServer = "http://albert.caslab.queensu.ca/";
        String phpString = "addItem.php?barcode='"+Long.toString(barcode)+"'&iName='"+ iName+"'&recType='"+rType+"'&bClr='"+bClr+"'&description='"+dtion + "'";
        //Toast.makeText(getApplicationContext(),albertServer+phpString,Toast.LENGTH_SHORT).show();
        String queryString;
        try {
            URL yahoo = new URL(albertServer+phpString);
            URLConnection yc = yahoo.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.e("chaos",albertServer+phpString);
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();
        long resultsFromScan[] = intent.getLongArrayExtra(BARCODE_MESSAGE);
        barcode = resultsFromScan[0];

        barcodeNumber = findViewById(R.id.barcodeNumber);
        barcodeNumber.setText(String.format(Long.toString(barcode)));
        itemName = (EditText)findViewById(R.id.itemName);
        recType = (EditText)findViewById(R.id.recType);
        binClr = (EditText)findViewById(R.id.BinColour);
        description = (EditText)findViewById(R.id.description);
        Button btn = findViewById(R.id.submitbtn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iName = itemName.getText().toString();
                rType = recType.getText().toString();
                bClr = binClr.getText().toString();
                dtion = description.getText().toString();
                //Toast.makeText(getApplicationContext(),iName + rType+ bClr + dtion,Toast.LENGTH_SHORT).show();
                insertToDatabase(iName,rType,bClr,dtion);
            }
        });
    }
}
