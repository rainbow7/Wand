import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController
import common.Context
import presentation.App

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun MainViewController() = ComposeUIViewController {
    val widthSizeClass = calculateWindowSizeClass().widthSizeClass
    App(Context(), widthSizeClass = widthSizeClass)
}