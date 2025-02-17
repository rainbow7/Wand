package presentation.ui.splash.view_model

import business.core.ViewEvent

/**
 * @author yuming
 */
sealed class LoginEvent : ViewEvent {
    data object Login : LoginEvent()
}