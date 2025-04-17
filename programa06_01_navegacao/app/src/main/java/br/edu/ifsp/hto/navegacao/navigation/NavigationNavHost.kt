package br.edu.ifsp.hto.navegacao.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.edu.ifsp.hto.navegacao.feature.screen.SecondScreen
import kotlinx.serialization.Serializable
import br.edu.ifsp.hto.navegacao.feature.screen.StartScreen

@Serializable
object StartScreenRoute

@Serializable
data class SecondScreenRoute(val message: String?)


@Composable
fun NavigationNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StartScreenRoute) {

        composable<StartScreenRoute> {
            StartScreen(
                navigateToSecondScreen = { message ->
                    navController.navigate(
                        SecondScreenRoute(
                            message = message
                        )
                    )
                }
            )
        }
        composable<SecondScreenRoute> { navBackStackEntry ->
            val secondScreenRoute = navBackStackEntry.toRoute<SecondScreenRoute>()

            SecondScreen(
                onBack = { navController.popBackStack() },
                message = secondScreenRoute.message
            )
        }

    }

}