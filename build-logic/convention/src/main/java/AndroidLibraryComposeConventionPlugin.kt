import com.android.build.gradle.LibraryExtension
import com.cmc.curtaincall.convention.configureComposeAndroid
import com.cmc.curtaincall.convention.configureKotlinAndroidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroidLibrary(this)
                configureComposeAndroid(this)
                defaultConfig.targetSdk = 34
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
                defaultConfig.vectorDrawables {
                    useSupportLibrary = true
                }
            }
        }
    }
}
