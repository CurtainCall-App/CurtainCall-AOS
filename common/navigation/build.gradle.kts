@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.curtaincall.android.library.compose)
}

android {
    namespace = "com.cmc.curtaincall.common.navigation"
}

dependencies {
    implementation(project(":common:designsystem"))
    implementation(project(":core:base"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // 3rd party compose UI
    implementation(libs.coil.compose)
    implementation(libs.calendar.compose)
    implementation(libs.bottomsheet.dialog)
}
