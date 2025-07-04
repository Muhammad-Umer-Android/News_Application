package com.application.newsappcompose.data.database


import com.application.newsappcompose.data.database.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    //Saving News
    suspend fun upsert(article: Article)
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article)

    //Caching News
    fun getAllArticles(): Flow<List<Article>>
    fun deleteAllAndInsertAll(articles: List<Article>)
}