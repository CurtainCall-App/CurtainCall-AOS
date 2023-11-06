import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.cmc.curtaincall.feature.auth"

    defaultConfig {
        resValue("string", "KAKAO_REDIRECT_SCHEME", "kakao${gradleLocalProperties(rootDir).getProperty("KAKAO_APP_KEY")}")
        resValue("string", "FACEBOOK_CLIENT_ID", gradleLocalProperties(rootDir).getProperty("FACEBOOK_CLIENT_ID"))
        resValue("string", "FACEBOOK_CLIENT_TOKEN", gradleLocalProperties(rootDir).getProperty("FACEBOOK_CLIENT_TOKEN"))
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":domain"))
    implementation(project(":feature:home"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.lifecycle.compose)
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

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
