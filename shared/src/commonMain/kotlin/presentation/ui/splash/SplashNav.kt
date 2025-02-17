package presentation.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject
import presentation.navigation.SplashNavigation
import presentation.ui.splash.view_model.LoginAction
import presentation.ui.splash.view_model.LoginViewModel

/**
 * @author yuming
 */
@Composable
internal fun SplashNav(
    viewModel: LoginViewModel = koinInject(),
    navigateToMain: () -> Unit
) {
    val navigator = rememberNavController()
    LaunchedEffect(viewModel) {
        viewModel.action.onEach { effect ->
            when (effect) {
                LoginAction.Navigation.NavigateToLogin -> {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login)
                }

                LoginAction.Navigation.NavigateToMain -> {
                    navigateToMain()
                }
            }
        }.collect {}
    }

    NavHost(
        startDestination = SplashNavigation.Splash,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<SplashNavigation.Splash> {
            SplashScreen()
        }
        composable<SplashNavigation.Login> {

        }
        composable<SplashNavigation.Register> {

        }
    }
}