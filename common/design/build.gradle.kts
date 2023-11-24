@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.curtaincall.android.library.compose)
}

android {
    namespace = "com.cmc.curtaincall.common.design"
}

dependencies {
    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.coil.compose)

    // 3rd party
    implementation(libs.calendar.compose)
    implementation(libs.bottomsheet.dialog)
}
