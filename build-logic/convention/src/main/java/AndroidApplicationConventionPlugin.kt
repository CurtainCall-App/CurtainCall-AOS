import com.android.build.api.dsl.ApplicationExtension
import com.cmc.curtaincall.convention.configureKotlinAndroidApplication
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroidApplication(this)
                defaultConfig.targetSdk = 33
            }
        }
    }
}
