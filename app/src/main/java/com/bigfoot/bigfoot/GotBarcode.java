package com.bigfoot.bigfoot;

public class GotBarcode {
    static{
        System.loadLibrary("gotBarcode");
        System.loadLibrary("RollingArray");
    }

    public GotBarcode(){
        init(); //initialize class in native cpp code
    };
    public native boolean barcodeMatch(long barcode);
    private native void init();
    public RollingArray ra;
}

