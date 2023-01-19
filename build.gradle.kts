import java.util.Properties
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    kotlin("multiplatform") version "1.8.0"
    id("maven-publish")
    id("signing")
}

group = "de.cketti.unicode"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    iosArm32()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    js(IR) {
        browser {}
    }

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    linuxArm64()
    linuxArm32Hfp()
    linuxX64()

    macosX64()
    macosArm64()

    mingwX64()
    mingwX86()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    wasm32()

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX86()
    watchosX64()
    watchosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val commonImplementation by creating {
            dependsOn(commonMain)
        }

        val jvmTest by getting {
            dependsOn(commonImplementation)
        }
    }

    targets.onEach {
        if (it.platformType != KotlinPlatformType.jvm) {
            it.compilations.getByName("main").source(sourceSets.getByName("commonImplementation"))
        }
    }
}

// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

// Grabbing secrets from local.properties file or from environment variables, which could be used on CI
val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    // Configure maven central repository
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("kotlin-codepoints")
            description.set("Kotlin Multiplatform (KMP) library that adds basic support for Unicode code points.")
            url.set("https://github.com/cketti/kotlin-codepoints")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("cketti")
                    name.set("cketti")
                    email.set("ck@cketti.de")
                }
            }
            scm {
                url.set("https://github.com/cketti/kotlin-codepoints")
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used
signing {
    sign(publishing.publications)
}
