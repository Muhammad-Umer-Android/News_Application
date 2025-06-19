package com.application.newsappcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.newsappcompose.common.dispatcher.DispatcherProvider
import com.application.newsappcompose.data.database.entity.Article
import com.application.newsappcompose.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.upsert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.deleteArticle(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

}