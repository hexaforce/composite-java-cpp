#!/bin/bash -ex
SCRIPT_DIR=$(cd $(dirname $0); pwd)

export JAVA_HOME=`/usr/libexec/java_home -v 1.8`\n
java -XstartOnFirstThread -jar swt/swt-application-jar-with-dependencies.jar
