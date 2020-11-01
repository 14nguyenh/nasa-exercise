FROM tomcat:9.0.39

MAINTAINER henry

COPY target/nasa-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
