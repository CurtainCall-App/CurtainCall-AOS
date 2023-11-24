import com.android.build.gradle.LibraryExtension
import com.cmc.curtaincall.convention.configureKotlinAndroidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroidLibrary(this)
                defaultConfig.targetSdk = 33
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}
