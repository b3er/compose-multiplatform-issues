import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }
    }
}

android {
    namespace = "com.github.b3er.cmp.issues"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.github.b3er.cmp.issues.resources"
        generateResClass = auto
    }
}
buildkonfig {
    packageName = "com.github.b3er.cmp.issues"

    defaultConfigs {
        buildConfigField(STRING, "kotlinVersion", libs.versions.kotlin.orNull)
        buildConfigField(STRING, "composeVersion", libs.versions.jetbrains.compose.orNull)
    }
}
