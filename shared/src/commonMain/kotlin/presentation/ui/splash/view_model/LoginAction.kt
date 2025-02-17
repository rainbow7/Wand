package presentation.ui.splash.view_model

import business.core.ViewSingleAction

/**
 * @author yuming
 */
sealed class LoginAction:ViewSingleAction {
    sealed class Navigation : LoginAction() {

        data object NavigateToMain : Navigation()

        data object NavigateToLogin : Navigation()

    }
}