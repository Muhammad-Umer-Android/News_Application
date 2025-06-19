package com.application.newsappcompose.ui.components


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.application.newsappcompose.data.database.entity.Article



@Composable
fun NewsLayout(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit
) {
    LazyColumn {
        items(newsList) {
            Article(it) { article ->
                articleClicked(article)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsLayoutWithDelete(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit,
    deleteArticle: (Article) -> Unit
) {
    LazyColumn {
        items(newsList, key = { article -> article.url!! }) { article ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart) || dismissState.isDismissed(
                    direction = DismissDirection.StartToEnd
                )
            ) {
                deleteArticle(article)
            }
            SwipeToDismiss(state = dismissState,
                background = {},
                dismissContent = {
                    Article(article) {
                        articleClicked(it)
                    }
                })

        }
    }
}