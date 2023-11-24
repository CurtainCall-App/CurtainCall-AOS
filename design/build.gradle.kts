@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.curtaincall.android.application)
    alias(libs.plugins.curtaincall.android.application.compose)
}

android {
    namespace = "com.cmc.curtaincall.design"

    defaultConfig {
        applicationId = "com.cmc.curtaincall.design"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":common:design"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.splashscreen)

    // compose
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // logging
    implementation(libs.timber)
}
