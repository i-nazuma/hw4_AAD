package at.technikum_wien.polzert.news.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import at.technikum_wien.polzert.news.R
import at.technikum_wien.polzert.news.viewmodels.NewsListViewModel

@Composable
fun MainScreen(navController: NavController, viewModel : NewsListViewModel) {
    val newsItems by viewModel.newsItems.observeAsState()
    val error by viewModel.error.observeAsState()
    val busy by viewModel.busy.observeAsState()
    var expanded by remember { mutableStateOf( false ) }

    Column {
        TopAppBar(title = {
            Text(text = stringResource(R.string.app_title))
        },
            actions = {
                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "menu")
                    DropdownMenu(expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(onClick = {
                            navController.navigate(Screen.SettingsScreen.route)
                            expanded = false
                        }) {
                            Text("Settings")
                        }
                    }
                }
            }
            )
        if (error == true)
            Text(text = stringResource(R.string.general_error))
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.reload()
            },
            enabled = !(busy ?: false)
        ) {
            Text(stringResource(R.string.reload))
        }
        LazyColumn(Modifier.fillMaxWidth()) {
            itemsIndexed(newsItems ?: listOf()) { index, newsItem ->
                NewsItemRow(navController = navController, index = index, newsItem = newsItem)

                }
            }
        }
    }

