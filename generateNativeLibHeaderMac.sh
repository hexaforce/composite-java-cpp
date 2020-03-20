#!/bin/bash -ex
SCRIPT_DIR=$(cd $(dirname $0); pwd)
cd $SCRIPT_DIR/jni/src

JAVA_HOME=`/usr/libexec/java_home -v 1.8`
cp $JAVA_HOME/include/jni.h .

javac -h . sdk/Obj.java
javac -h . sdk/Param.java
javac -h . sdk/Say.java
