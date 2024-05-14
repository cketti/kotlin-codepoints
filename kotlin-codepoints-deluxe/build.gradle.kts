plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.vanniktech.maven.publish)
}

kotlin {
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

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

    linuxArm64()
    linuxX64()

    macosX64()
    macosArm64()

    mingwX64()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    @Suppress("OPT_IN_USAGE")
    wasmJs()
    @Suppress("OPT_IN_USAGE")
    wasmWasi()

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX64()
    watchosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kotlin-codepoints"))
            }
        }
        
        commonTest {
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
