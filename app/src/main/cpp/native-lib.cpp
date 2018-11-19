#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_bigfoot_bigfoot_MainActivity_getBinType(
        JNIEnv *env,
        jobject /* this */, jlong UPC) {

    std::string binType;
    unsigned long long int gatorade = 55577420751;

    if(UPC == gatorade){
        binType = "Blue Bin";
    }
    if(UPC == 2600136000007){
        binType = "Garbage";
    }
    else {
        binType = "Could not find";
    }
    return env->NewStringUTF(binType.c_str());
}

