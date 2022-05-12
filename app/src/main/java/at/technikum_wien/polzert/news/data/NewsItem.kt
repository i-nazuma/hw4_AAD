package at.technikum_wien.polzert.news.data

import kotlinx.datetime.Instant

@kotlinx.serialization.Serializable
data class NewsItem(var identifier : String, var title : String, var link : String?, var description : String?, var imageUrl : String?, var author : String?, var publicationDate : Instant, var keywords : Set<String>)
