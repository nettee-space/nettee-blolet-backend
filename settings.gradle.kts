rootProject.name = "nettee-blolet-backend"

val services = "${rootProject.projectDir}/services"

apply(from = "common/common.settings.gradle.kts")
apply(from = "core/core.settings.gradle.kts")
apply(from = "monolith/monolith.settings.gradle.kts")
