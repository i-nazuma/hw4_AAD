package at.technikum_wien.polzert.news.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.technikum_wien.polzert.news.data.NewsItem
import at.technikum_wien.polzert.news.ui.theme.NewsTheme
import at.technikum_wien.polzert.news.viewmodels.NewsListViewModel

@Composable
fun Navigation(viewModel : NewsListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.DetailScreen.route + "/{newsItemIndex}",
            arguments = listOf(
                navArgument(name = "newsItemIndex") {
                    type = NavType.IntType
                }
            )
        ) {
            var item : NewsItem? = null
            val index = it.arguments?.getInt("newsItemIndex")
            if (index != null && (viewModel.newsItems.value?.size ?:0) > index)
                item = viewModel.newsItems.value?.get(index)
            DetailScreen(navController = navController, newsItem = item)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsTheme {
        Navigation(NewsListViewModel())
    }
}
