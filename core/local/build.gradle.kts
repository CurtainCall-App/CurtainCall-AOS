@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.library)
    alias(libs.plugins.curtaincall.android.hilt)
    alias(libs.plugins.curtaincall.android.room)
}

android {
    namespace = "com.cmc.curtaincall.core.local"
}

dependencies {
    implementation(project(":domain"))

    // logging
    implementation(libs.timber)

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)
}
