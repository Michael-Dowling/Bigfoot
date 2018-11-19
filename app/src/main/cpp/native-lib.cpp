#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_bigfoot_bigfoot_ScanActivity_getBinType(
        JNIEnv *env,
        jobject /* this */, jlong UPC) {

    std::string binType;
    long long int upc = (long long int) UPC;
    long long int gatorade = 55577420751;

    if(upc == 55577420751){
        binType = "Blue Bin";
    }
    else if(upc == 2500136000007){
        binType = "Garbage";
    }
    else {
        binType = std::to_string(UPC);
    }
    return env->NewStringUTF(binType.c_str());
}

