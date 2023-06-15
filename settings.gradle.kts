pluginManagement {
    repositories {
        google()
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
rootProject.name = "FootballScore"
include(":app")
include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:uicomponents")
include(":feature:matches")
include(":feature:matchdetails")
include(":feature:leaguedetails")
include(":core:database")
include(":feature:watchedmatches")
include(":feature:news")
