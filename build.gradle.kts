import org.gradle.kotlin.dsl.java

plugins {
    java
    id("io.openapiprocessor.openapi-processor") version "2025.1.1"
}

group = "net.ivoa.calycopis"

openapiProcessor {
    apiPath(layout.projectDirectory.file("schema/v1.0/Calycopis-broker.yaml"))
    process("spring") {
        targetDir(layout.buildDirectory.dir("openapi"))
        processor("io.openapiprocessor:openapi-processor-spring:2026.1")
        prop("mapping", layout.projectDirectory.file("mapping.yaml"))

    }
}

// generate api resource before processing
tasks.withType<ProcessResources> {
    dependsOn("processSpring")
}

java {
    toolchain {
        languageVersion =JavaLanguageVersion.of(17)
    }
}
