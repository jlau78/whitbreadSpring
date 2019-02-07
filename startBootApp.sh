snapshotJar=demo-0.0.1-SNAPSHOT.jar

echo " SpringBoot application ${snapshotJar}......"

JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n "

#JAVA_OPTS=" ${JAVA_OPTS} -XX:+UseG1GC"
#JAVA_OPTS=" ${JAVA_OPTS} -verbose:gc  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:./logs/gc-demo.log"


java ${JAVA_OPTS} -jar  target/${snapshotJar} 2> /dev/null

