#!/bin/bash -ex
SCRIPT_DIR=$(cd $(dirname $0); pwd)
cd $SCRIPT_DIR/jni/src

javac -h . sdk/Obj.java
javac -h . sdk/Param.java
javac -h . sdk/Say.java
