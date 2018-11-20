//
// Created by Cache Angus on 2018-11-19.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jobjectArray JNICALL
Java_com_bigfoot_bigfoot_InstructionActivity_getInstr(JNIEnv *env) {


   //this was a simple example of integratin c++ into the project
    std::string in1 = "Welcome to BIGFOOT, your go to recycling app!";
    std::string in2 = "Simply scan your item's barcode and follow the recycling instructions.";
    std::string in3 = "Checkout the menu for other features.";
    std::string in4 = "We're here to help you and the planet!";

    jobjectArray inst;

    jobject fill[4];

    fill[0] = in1;
    fill[1] = in2;
    fill[2] = in3;
    fill[3] = in4;

    inst = (*env)->NewObjectArray(env,4);
    (*env)->SetObjectArrayRegion(env, inst, 0, 4, fill);


    return inst;
   }

/*


