package com.bigfoot.bigfoot;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import me.dm7.barcodescanner.zbar.ZBarScannerView;



public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    private GetBarcode gb;
    private static String item;
    private static String recycleType;
    private static String binType;
    private static String description;


    static{
        //load native cpp libraries
        System.loadLibrary("native-lib");
        System.loadLibrary("GetBarcode");
        System.loadLibrary("RollingArray");
    }
    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            android.support.v4.app.ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        }
        gb = new GetBarcode();

        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        long upc = Long.parseLong(result.getContents());    //parse long upc from string results






        //call native c++ code to determine if there is a barcode match
        if(gb.barcodeMatch(upc)){
            gotBarcode(result.getContents());    //got a barcode match, call method to deal with it
            Intent i = new Intent(this, ResultsActivity.class);
            startActivity(i);
            //onBackPressed();
        }

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
    public static String getItem(){
        return item;
    }
    public static String getBinType(){
        return binType;
    }
    public static String getRecycleType(){
        return recycleType;
    }
    public void gotBarcode(String barcode){
        long code = Long.parseLong(barcode);
        //String type = getBinTypeFromUpc(code);
        //ResultsActivity.binType.setText(getBinTypeFromUpc(code));

        //TextView item = findViewById(R.id.itemName);
        //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
       // String hey = "Hello";
       // String ho = "Hi";
       // String wooo = "It worked";
       // item = "hello";

        //REPLACE THESE @ CHRISOTPHER
        binType = getBinTypeFromUpc(code);
        item = getNameFromUpc(code);
        recycleType = getRecycleTypeFromUpc(code);

        //ResultsActivity.item1.setText("HELLO!");
        //MainActivity.tvresult.setText("HELLO!");
        //ResultsActivity.item.setText(getNameFromUpc(code));
        //ResultsActivity.recycleType.setText(getRecycleTypeFromUpc(code));
        //ResultsActivity.binType.setText(type);

    }

    public native String getBinTypeFromUpc(long UPC);
    public native String getNameFromUpc(long UPC);
    public native String getRecycleTypeFromUpc(long UPC);
    //public native String getBinTypeFromName(String UPC);
}