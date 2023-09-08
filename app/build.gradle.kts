import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.cmc.curtaincall"

    defaultConfig {
        applicationId = "com.cmc.curtaincall"
        versionCode = 8
        versionName = "1.0"

        buildConfigField("String", "GET_STREAM_API_KEY", gradleLocalProperties(rootDir).getProperty("GET_STREAM_API_KEY"))
        buildConfigField("String", "KAKAO_APP_KEY", gradleLocalProperties(rootDir).getProperty("KAKAO_APP_KEY"))
        resValue("string", "NAVER_API_MAP_KEY", gradleLocalProperties(rootDir).getProperty("NAVER_API_MAP_KEY"))
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":common:design"))
    implementation(project(":core:base"))
    implementation(project(":core:network"))
    implementation(project(":core:local"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:mypage"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.splashscreen)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.accompanist.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.getstream.chat.android.compose)

    // logging
    implementation(libs.timber)

    // auth
    implementation(libs.kakao.user)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.junit)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
