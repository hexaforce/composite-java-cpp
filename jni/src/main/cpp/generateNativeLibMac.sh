#!/bin/bash -ex
SCRIPT_DIR=$(cd $(dirname $0); pwd)
cd $SCRIPT_DIR

JDK_DIR=`/usr/libexec/java_home -v 11`
cp -f $JDK_DIR/include/jni.h .
cp -f $JDK_DIR/include/darwin/jni_md.h .

# Create the header with javac -h . ClassName.java
# Remember to set your JAVA_HOME env var

g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin com_baeldung_jni_HelloWorldJNI.cpp -o com_baeldung_jni_HelloWorldJNI.o
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin com_baeldung_jni_ExampleParametersJNI.cpp -o com_baeldung_jni_ExampleParametersJNI.o
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin com_baeldung_jni_ExampleObjectsJNI.cpp -o com_baeldung_jni_ExampleObjectsJNI.o
g++ -dynamiclib -o ../../../native/macos/libnative.dylib com_baeldung_jni_HelloWorldJNI.o com_baeldung_jni_ExampleParametersJNI.o com_baeldung_jni_ExampleObjectsJNI.o -lc
