import com.cmc.curtaincall.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
                apply("com.google.dagger.hilt.android")
            }
            dependencies {
                add("implementation", libs.findLibrary("hilt-android").get())
                add("kapt", libs.findLibrary("hilt-android-compiler").get())
                add("implementation", libs.findLibrary("hilt-navigation-compose").get())
            }
        }
    }
}
