#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_bigfoot_bigfoot_ScanActivity_getBinType(
        JNIEnv *env,
        jobject /* this */, jlong UPC) {

    //you caught us, the following is some ugly code that we did not want to write...
    //Issues setting up the database forced us to resort to a series of if statements...
    //If this project was carried forward we probably would not have used c++ and we would've moved
    //all of this code to a database. It should be noted that we did write the code for the database
    //we just couldn't get it working. Anyways, enjoy our if statements!
    std::string binType;
    long long int upc = (long long int) UPC;
    long long int gatorade = 55577420751;
    long long int fiestaSaladFromArc = 2500136000007;
    long long int natureValleyGranolaBar = 6563350226;

    if(upc == gatorade){
        binType = "Blue Bin";
    }
    else if(upc == fiestaSaladFromArc){
        binType = "Blue Bin. Ensure all contents are clean";
    }
    else if(upc == natureValleyGranolaBar){
        binType = "Garbage;";
    }
    else {
        binType = std::to_string(UPC);
    }
    return env->NewStringUTF(binType.c_str());
}

/*
extern "C" JNIEXPORT jstring JNICALL
Java_com_bigfoot_bigfoot_ScanActivity_getBinType(
        JNIEnv *env,
        jobject , jstring name) {

    const char *str = (*env)->GetStringUTFChars(env, name , 0);

    //figure out how to compare jstrings to cstrings
    std::string binType;
    long long int upc = (long long int) UPC;
    long long int gatorade = 55577420751;
    long long int fiestaSaladFromArc = 2500136000007;
    long long int natureValleyGranolaBar = 6563350226;



    if(upc == gatorade){
        binType = "Blue Bin";
    }
    else if(upc == fiestaSaladFromArc){
        binType = "Blue Bin. Ensure all contents are clean";
    }
    else if(upc == natureValleyGranolaBar){
        binType = "Garbage;";
    }
    else {
        binType = std::to_string(UPC);
    }
    return env->NewStringUTF(binType.c_str());
}
*/