package presentation.navigation

import kotlinx.serialization.Serializable

/**
 * @author yuming
 */
sealed interface AppNavigation {
    @Serializable
    data object Splash : AppNavigation

    @Serializable
    data object Main : AppNavigation
}