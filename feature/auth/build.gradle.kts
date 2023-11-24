import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.library.compose)
    alias(libs.plugins.curtaincall.android.hilt)
}

android {
    namespace = "com.cmc.curtaincall.feature.auth"

    defaultConfig {
        resValue("string", "KAKAO_REDIRECT_SCHEME", "kakao${gradleLocalProperties(rootDir).getProperty("KAKAO_APP_KEY")}")
        resValue("string", "FACEBOOK_CLIENT_ID", gradleLocalProperties(rootDir).getProperty("FACEBOOK_CLIENT_ID"))
        resValue("string", "FACEBOOK_CLIENT_TOKEN", gradleLocalProperties(rootDir).getProperty("FACEBOOK_CLIENT_TOKEN"))
    }
}

dependencies {
    implementation(project(":common:design"))
    implementation(project(":core:base"))
    implementation(project(":domain"))
    implementation(project(":feature:home"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx)

    // compose
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.webview)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)

    // logging
    implementation(libs.timber)

    // auth
    implementation(libs.kakao.user)
    implementation(libs.facebook.login)
}
