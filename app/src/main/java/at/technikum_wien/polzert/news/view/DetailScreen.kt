package at.technikum_wien.polzert.news.view

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.net.Uri
import android.os.Build
import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum_wien.polzert.news.R
import at.technikum_wien.polzert.news.data.NewsItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(navController: NavController, newsItem : NewsItem?) {
    val scrollState = rememberScrollState()

    Column {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_title))
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Column (
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier
                .height(270.dp)
                .padding(20.dp)) {
                GlideImage(
                    imageModel = newsItem?.imageUrl,
                    modifier = Modifier.fillMaxWidth(1f),
                    contentScale = ContentScale.Fit
                )
                Surface(
                    color = Color.LightGray.copy(0.75f),
                    modifier = Modifier.padding(20.dp)
                ) {
                    Column() {
                        Text(text = newsItem?.title ?: "", fontSize = 20.sp)
                        Text(text = newsItem?.author ?: "")
                        Text(text = newsItem?.publicationDate?.toString() ?: "")
                    }
                }
            }

            Text(text = "Description", fontWeight = FontWeight.Bold)
            Text(text = newsItem?.description ?: "")
            Text(text = "Author", fontWeight = FontWeight.Bold)
            Text(text = newsItem?.author ?: "")

            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(newsItem?.link)) }
            Button(onClick = { context.startActivity(intent) }) {
                Text(text = "Click to read full article")
            }
        }
    }

    newsItem?.description = Html.fromHtml(Html.fromHtml(newsItem?.description).toString()).toString()
}
