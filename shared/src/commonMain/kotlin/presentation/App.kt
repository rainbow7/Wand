package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.bundle.Bundle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.eygraber.uri.UriCodec
import common.Context
import di.appModule
import router.CustomType
import router.DetailRoute
import router.HomeRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import presentation.component.ContentWithTitleBar
import presentation.navigation.AppNavigation
import presentation.theme.AppTheme
import presentation.ui.main.MainNav
import presentation.ui.splash.SplashNav
import kotlin.reflect.typeOf

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(context: Context, widthSizeClass: WindowWidthSizeClass) {
    KoinApplication(application = {
        modules(appModule(context))
    }) {

        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        AppTheme(useDarkTheme = false) {
            val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
            val navigator = rememberNavController()

            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Splash,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable<AppNavigation.Splash> {
                        SplashNav(navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Main)
                        })
                    }
                    composable<AppNavigation.Main> {
                        MainNav {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Splash)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    ContentWithTitleBar(
        titleText = "我是标题",
        content = {

        }
    )
}



@Preview
@Composable
fun HomeView(navController: NavHostController) {
    // Home view
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home view")
            Button(
                onClick = {
                    navController.navigate(
                        DetailRoute(
                            item = CustomType(
                                "1",
                                "Item 1",
                                "Description 1"
                            )
                        )
                    )
                }
            ) {
                Text("Go to Item 1")
            }
            Button(
                onClick = {
                    navController.navigate(
                        DetailRoute(
                            item = CustomType(
                                "2",
                                "Item 2",
                                "Description 2"
                            )
                        )
                    )
                }
            ) {
                Text("Go to Item 2")
            }
        }

    }
}

@Composable
fun DetailView(navController: NavHostController, item: CustomType) {
    // Detail view
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Detail view")
            Text("Item id: ${item.id}")
            Text("Item name: ${item.name}")
            Text("Item description: ${item.description}")
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Go back")
            }
        }

    }
}

@Composable
fun createNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        homeRoute(navController)
        detailRoute(navController)
    }
}


fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable<HomeRoute> {
        HomeView(navController = navController)
    }
}

fun NavGraphBuilder.detailRoute(navController: NavHostController) {
    composable<DetailRoute>(
        //like this
        typeMap = mapOf(typeOf<CustomType>() to CustomNavType),
    ) {
        val item = it.toRoute<DetailRoute>().item
        DetailView(navController = navController, item = item)
    }
}

val CustomNavType = object : NavType<CustomType>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): CustomType? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): CustomType {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: CustomType) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: CustomType): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}

