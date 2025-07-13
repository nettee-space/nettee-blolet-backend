rootProject.name = "nettee-blolet-backend"

val services = "${rootProject.projectDir}/services"

apply(from = "common/common.settings.gradle.kts")
apply(from = "core/core.settings.gradle.kts")
apply(from = "monolith/monolith.settings.gradle.kts")

// services
apply(from = "$services/series/series.settings.gradle.kts")
apply(from = "$services/article/article.settings.gradle.kts")