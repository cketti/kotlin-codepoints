plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.vanniktech.maven.publish)
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
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    linuxArm32Hfp()
    linuxArm64()
    linuxMips32()
    linuxMipsel32()
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
        val commonMain by getting {
            dependencies {
                api(project(":kotlin-codepoints"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    pom {
        name.set("kotlin-codepoint-deluxe")
        description.set("Kotlin Multiplatform (KMP) library that adds a nicer API than kotlin-codepoint for dealing with Unicode code points.")
    }
}

tasks.create("publishMips") {
    dependsOn(
        "publishLinuxMips32PublicationToMavenCentralRepository",
        "publishLinuxMipsel32PublicationToMavenCentralRepository"
    )
}
