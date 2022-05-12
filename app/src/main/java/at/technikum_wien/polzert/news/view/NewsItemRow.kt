package at.technikum_wien.polzert.news.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.navigation.NavController
import at.technikum_wien.polzert.news.data.NewsItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsItemRow(navController : NavController, index : Int, newsItem : NewsItem) {
    if(index == 0) {
        Box(modifier = Modifier
            .height(250.dp)
            .padding(20.dp)
            .fillMaxWidth(1f)) {
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
                    ClickableText(
                        text = AnnotatedString(text = newsItem.title, SpanStyle(fontWeight = Bold)),
                        onClick = {
                            navController.navigate(Screen.DetailScreen.route + "/${index}")

                        })
                    Text(text = newsItem.author ?: "")
                    Text(text = newsItem.publicationDate.toString() ?: "")
                }
            }
        }
    }else{
        Row() {
            Box() {
                GlideImage(
                    imageModel = newsItem.imageUrl,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )}

                Column(
                    modifier = Modifier.padding(top = 37.dp, start = 10.dp)
                ) {
                    ClickableText(
                        text = AnnotatedString(text = newsItem.title, SpanStyle(fontWeight = Bold)),
                        onClick = {
                            navController.navigate(Screen.DetailScreen.route + "/${index}")

                        })
                    Text(text = newsItem.author ?: "")
                    Text(text = newsItem.publicationDate.toString() ?: "")
                }
        }
    }
}
