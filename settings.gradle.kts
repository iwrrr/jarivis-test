pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Jarivis Test"
include(":app")
include(":core:core-common")
include(":core:core-data")
include(":core:core-ui")
include(":feature:feature-movie")
include(":feature:feature-search")
include(":feature:feature-popular-tv")
