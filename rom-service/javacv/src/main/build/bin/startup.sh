#!/bin/sh
#check JAVA_HOME & java
CURR_DIR=`pwd`
cd `dirname "$0"`/..
JOB_HOME=`pwd`
cd $CURR_DIR
if [ -z "$JOB_HOME" ] ; then
    echo
    echo "Error: JOB_HOME environment variable is not defined correctly."
    echo
    exit 1
fi


if [ -z "$JAVA_HOME" ]; then
  export JAVA=`which java`
else
  export JAVA="$JAVA_HOME/bin/java"
fi
export JMX_PORT=9123
export CLASSPATH=$JOB_HOME/conf:$(ls $JOB_HOME/lib/*.jar | tr '\n' :)
UEAP_JVM_ARGS="-Xmx512m -Xms256m -server -Dlog4j_root=$JOB_HOME/logs"
UEAP_JVM_ARGS="$UEAP_JVM_ARGS -cp $CLASSPATH"

#startup Service
nohup $JAVA $UEAP_JVM_ARGS com.netposa.JavaCvApplication > $JOB_HOME/logs/service.log 2>&1 &
echo OK!