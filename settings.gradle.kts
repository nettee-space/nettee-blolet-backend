rootProject.name = "nettee-blolet-backend"

val services = "${rootProject.projectDir}/services"

apply(from = "common/common.settings.gradle.kts")
apply(from = "core/core.settings.gradle.kts")
apply(from = "monolith/monolith.settings.gradle.kts")

apply(from = "services/blog/blog.settings.gradle.kts")

// services
apply(from = "$services/article/article.settings.gradle.kts")
apply(from = "$services/auth/auth.settings.gradle.kts")
apply(from = "$services/notification/notification.settings.gradle.kts")
apply(from = "$services/profile/profile.settings.gradle.kts")
