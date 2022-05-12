package at.technikum_wien.polzert.news.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
//import at.technikum_wien.polzert.news.data.createDummyNews
import at.technikum_wien.polzert.news.ui.theme.NewsTheme
import at.technikum_wien.polzert.news.view.Navigation
import at.technikum_wien.polzert.news.viewmodels.NewsListViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<NewsListViewModel>()

        setContent {
            NewsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(viewModel = viewModel)
                }
            }
        }

    }
}
