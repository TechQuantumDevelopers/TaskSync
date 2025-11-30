package com.techquantum.tasksync.modules.onboarding.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class OnboardingRoute(val route: String) {
    object Onboarding : OnboardingRoute("onboarding")
}
