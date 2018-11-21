package com.bigfoot.bigfoot;
import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    private Long[] listOfInts = new Long[100];
    private int results = 0;

    static{
        System.loadLibrary("native-lib");
    }
    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            android.support.v4.app.ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        }
        for (int i = 0; i< 100; i++){
            listOfInts[i] = Long.valueOf(0);
        }
        results = 0;
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
        results = results +1;
        if (results<100){
            listOfInts[results] = Long.parseLong(result.getContents());
            //Log.v("hello", listOfInts[results].toString());
            // Log.v("hello", String.valueOf(ContinueScanning));

            int matchCount = 0;
            for (int j=0; j < results; j++){
                //Log.v("compare", String.valueOf(listOfInts[j].equals(listOfInts[results])));
                // Log.v("compare", String.valueOf(listOfInts[j]) + String.valueOf(listOfInts[results]));

                if (listOfInts[j].equals(listOfInts[results])){
                    matchCount = matchCount +1;
                }
            }
            //Log.v("matchCount", String.valueOf(matchCount));
            if (matchCount >1){
                gotBarcode(result.getContents());
                onBackPressed();
            }

        }
        else{
            MainActivity.tvresult.setText("Error reading, scan again.");
            onBackPressed();
        }

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
    public void gotBarcode(String barcode){
        long code = Long.parseLong(barcode);
        //String type = getBinTypeFromUpc(code);
        ResultsActivity.binType.setText(getBinTypeFromUpc(code));
        ResultsActivity.item.setText(getNameFromUpc(code));
        ResultsActivity.recycleType.setText(getRecycleTypeFromUpc(code));
        //ResultsActivity.binType.setText(type);

    }

    public native String getBinTypeFromUpc(long UPC);
    public native String getNameFromUpc(long UPC);
    public native String getRecycleTypeFromUpc(long UPC);
    //public native String getBinTypeFromName(String UPC);
}