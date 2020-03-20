#!/bin/bash -ex
SCRIPT_DIR=$(cd $(dirname $0); pwd)
cd $SCRIPT_DIR/jni/src

JAVA_HOME=`/usr/libexec/java_home -v 1.8`

TARGET=sdk_Say
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin ${TARGET}.cpp -o ${TARGET}.o
TARGET=sdk_Param
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin ${TARGET}.cpp -o ${TARGET}.o
TARGET=sdk_Obj
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin ${TARGET}.cpp -o ${TARGET}.o

g++ -dynamiclib -o ../native/macos/libnative.dylib sdk_Say.o sdk_Param.o sdk_Obj.o -lc
