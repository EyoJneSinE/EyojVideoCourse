pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EyojVideoCourse"
include(":app")
include(":navigationcourseapp")
include(":core:common")
include(":feature:main:courselist")
include(":feature:main:coursecommunicator")
include(":core:data")
include(":core:domain")
include(":feature:main:coursedetail")
include(":feature:main:coursevideo")
include(":feature:main:auth")
