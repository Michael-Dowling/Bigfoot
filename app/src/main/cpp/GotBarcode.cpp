#include<string>
#include<deque>
#include "jni.h"

extern "C" {
JNIEXPORT void JNICALL
Java_com_bigfoot_bigfoot_GotBarcode_init(JNIEnv *env, jobject obj){
    jclass jcls = env->FindClass("com/bigfoot/bigfoot/GotBarcode");
    jfieldID valId = env->GetFieldID(jcls, "ra", "Lcom/bigfoot/bigfoot/RollingArray;");

    //create RollingArray object
    jclass rolArr = env->FindClass("com/bigfoot/bigfoot/RollingArray");
    //get constructor ID
    jmethodID constructor = env->GetMethodID(rolArr,"<init>","(I)V");
    //create jobject
    jobject ra = env->NewObject(rolArr,constructor,100);
    //set object field in GotBarcode class
    env->SetObjectField(obj,valId,ra);
}

JNIEXPORT jboolean JNICALL
Java_com_bigfoot_bigfoot_GotBarcode_barcodeMatch(JNIEnv *env, jobject obj, jlong barcode){

    jclass gbCls = env->FindClass("com/bigfoot/bigfoot/GotBarcode");
    jfieldID raID = env->GetFieldID(gbCls, "ra", "Lcom/bigfoot/bigfoot/RollingArray;");
    jobject ra = env->GetObjectField(obj,raID); //get RollingArray object

    jclass raCls = env->FindClass("com/bigfoot/bigfoot/RollingArray");
    jmethodID getNumOccID = env->GetMethodID(raCls,"getNumOccurences","(J)I");

    if(env->CallIntMethod(ra,getNumOccID,barcode)>5){
        return (jboolean) true;
    }else {
        jmethodID add = env->GetMethodID(raCls, "add", "(J)V");
        env->CallVoidMethod(ra, add, barcode);   //add barcode to list of previous UPC's
        return (jboolean) false;
    }
}

}





