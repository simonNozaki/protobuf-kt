import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("com.google.protobuf") version "0.8.17"
    idea
}

version = "1.0.0"

idea {
    module {
        generatedSourceDirs.add(file("src/main/java"))
        sourceDirs.add(file("src/main/java"))
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.protobuf:protobuf-java:3.11.1")
    implementation("com.google.code.gson:gson:2.8.7")
}

/**
 * protobufコード生成タスク
 */
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.11.1"
    }

    generatedFilesBaseDir = "$projectDir/src"

    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.doFirst {
                delete("$generatedFilesBaseDir/main/java")
            }
        }
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
