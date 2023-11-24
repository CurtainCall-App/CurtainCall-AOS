import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.application)
    alias(libs.plugins.curtaincall.android.application.compose)
    alias(libs.plugins.curtaincall.android.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.cmc.curtaincall"

    defaultConfig {
        applicationId = "com.cmc.curtaincall"
        versionCode = 11
        versionName = "1.0"

        buildConfigField("String", "GET_STREAM_API_KEY", gradleLocalProperties(rootDir).getProperty("GET_STREAM_API_KEY"))
        buildConfigField("String", "KAKAO_APP_KEY", gradleLocalProperties(rootDir).getProperty("KAKAO_APP_KEY"))
        resValue("string", "NAVER_API_MAP_KEY", gradleLocalProperties(rootDir).getProperty("NAVER_API_MAP_KEY"))
        vectorDrawables {
            useSupportLibrary = true
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
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.getstream.chat.android.compose)

    // logging
    implementation(libs.timber)

    // auth
    implementation(libs.kakao.user)

    // test
    debugImplementation(libs.androidx.compose.ui.tooling)
}
