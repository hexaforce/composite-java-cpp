#include "sdk_Say.h"
#include "iostream"

/*
 * Class:     sdk_Say
 * Method:    sayHello
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_sdk_Say_sayHello(JNIEnv *env, jobject thisObject)
{
    std::string hello = "Hello from C++ !!";
    std::cout << hello << std::endl;
    return env->NewStringUTF(hello.c_str());
}