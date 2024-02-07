@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.library.compose)
}

android {
    namespace = "com.cmc.curtaincall.common.designsystem"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:base"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // 3rd party compose UI
    implementation(libs.coil.compose)
    implementation(libs.calendar.compose)
    implementation(libs.bottomsheet.dialog)
    implementation(libs.accompanist.systemuicontroller)
}
