package presentation.ui.splash.view_model

import business.core.BaseViewModel

/**
 * @author yuming
 */
class LoginViewModel:BaseViewModel<LoginEvent,LoginState,LoginAction>() {
    override fun setInitialState(): LoginState = LoginState()

    override fun onTriggerEvent(event: LoginEvent) {

    }
}