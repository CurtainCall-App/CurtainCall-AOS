@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.library)
    alias(libs.plugins.curtaincall.android.hilt)
}

android {
    namespace = "com.cmc.curtaincall.common.utility"
}

dependencies {
    implementation(project(":domain"))

    // androidx
    implementation(libs.androidx.core.ktx)

    // 3rd party compose UI
    implementation(libs.calendar.compose)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
}
