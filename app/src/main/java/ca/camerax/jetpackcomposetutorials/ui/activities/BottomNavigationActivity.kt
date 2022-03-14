package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ca.camerax.jetpackcomposetutorials.ui.fragments.HomeScreen
import ca.camerax.jetpackcomposetutorials.ui.fragments.LearningScreen
import ca.camerax.jetpackcomposetutorials.util.BottomNavigationItem


class BottomNavigationActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FillNavController()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FillNavController() {
    val navController = rememberNavController()

    SetupScaffold(topBar = universalScaffoldTopAppBar(
        topAppBarTitle = "Bottom Navigation Bar",
        //actions = topAppbarActions()
    ), bottomBar = { BottomNavigationBar(navController) }) {
        Navigation(navController)
    }
}


@Composable
private fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Learn,
        BottomNavigationItem.Resources,
        BottomNavigationItem.Present,
        BottomNavigationItem.More
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primarySurface,
        //contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {

                        /**
                         * Pop up to the start destination of the graph to avoid building up
                         * a large stack of destinations on the back stack as users select items
                         * */

                        navController.graph.startDestinationRoute.let { route ->
                            route?.let {
                                popUpTo(route = it) {
                                    saveState = true
                                }
                            }
                        }

                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(text = item.title) })
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.Home.route) {
        composable(BottomNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavigationItem.Learn.route) {
            LearningScreen()
        }
        composable(BottomNavigationItem.Resources.route) {
            HomeScreen()
        }
        composable(BottomNavigationItem.Present.route) {
            LearningScreen()
        }
        composable(BottomNavigationItem.More.route) {
            HomeScreen()
        }
    }
}

