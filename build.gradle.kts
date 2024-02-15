import com.bmuschko.gradle.tomcat.tasks.TomcatRun

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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    val tomcatVersion = "8.0.42"
    tomcat("org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}")
    tomcat("org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}")
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}")
}

tomcat.httpPort = 8080
tomcat.contextPath = "/"

tasks.named<TomcatRun>("tomcatRun") {
   doFirst {
       System.setProperty("USER_FILENAME", "/Users/tymefighter/Downloads/project-run/users.json")
       System.setProperty("USER_PERSISTENCE_TIME_MS", "10000")
   }
}

tasks.test {
    useJUnitPlatform()
}
