#!/bin/sh

# export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/"
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/"
# export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/"

# export MAVEN_OPTS="--add-modules java.annotations.common"

echo "当前使用的JAVA的HOME目录是：$JAVA_HOME"
echo `javac -version`
export PATH=$JAVA_HOME/bin/:$PATH
echo `javac -version`

echo "\n 查看当前maven的环境变量"
mvn --version

echo "\n 开始针对项目进行打包"
mvn clean package