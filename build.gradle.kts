import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    kotlin("multiplatform") version "1.8.0"
    id("com.vanniktech.maven.publish") version "0.23.2"
}

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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val commonImplementation by creating {
            dependsOn(commonMain)
        }
    }

    targets.onEach {
        if (it.platformType != KotlinPlatformType.jvm) {
            it.compilations.getByName("main").source(sourceSets.getByName("commonImplementation"))
        }
    }
}

tasks.create("publishMips") {
    dependsOn(
        "publishLinuxMips32PublicationToMavenCentralRepository",
        "publishLinuxMipsel32PublicationToMavenCentralRepository"
    )
}
