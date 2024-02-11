plugins {
    id("java")
    id("war")
    id("com.bmuschko.tomcat") version "2.7.0"
}

group = "org.uapp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")

    val tomcatVersion = "8.0.42"
    tomcat("org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}")
    tomcat("org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}")
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}")
}

tomcat.httpPort = 8080
tomcat.contextPath = "/"
