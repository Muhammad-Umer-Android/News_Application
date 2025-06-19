package com.application.newsappcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.newsappcompose.data.database.dao.ArticleDao
import com.application.newsappcompose.data.database.dao.SavedArticleDao
import com.application.newsappcompose.data.database.entity.Article
import com.application.newsappcompose.data.database.entity.SavedArticleEntity


@Database(entities = [SavedArticleEntity::class, Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getArticleDao(): ArticleDao

}