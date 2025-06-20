package com.application.newsappcompose.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.application.newsappcompose.common.NoInternetException
import com.application.newsappcompose.common.dispatcher.DispatcherProvider
import com.application.newsappcompose.common.logger.Logger
import com.application.newsappcompose.common.networkhelper.NetworkHelper
import com.application.newsappcompose.common.utils.Const
import com.application.newsappcompose.common.utils.ValidationUtil.checkIfValidArgNews
import com.application.newsappcompose.data.database.entity.Article
import com.application.newsappcompose.data.repository.NewsRepository
import com.application.newsappcompose.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
    private val pager: Pager<Int, Article>,
    private val logger: Logger,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private var pageNum = Const.DEFAULT_PAGE_NUM
    private val _newsItem = MutableStateFlow<UIState<List<Article>>>(UIState.Empty)
    val newsItem: StateFlow<UIState<List<Article>>> = _newsItem

    private val _newsItemPaging = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsItemPaging: StateFlow<PagingData<Article>> = _newsItemPaging

    init {
        fetchNews()
    }

    fun fetchNews() {
        if (checkIfValidArgNews(savedStateHandle.get("country") as? String?)) {
            fetchNewsByCountry(savedStateHandle.get("country"))
        } else if (checkIfValidArgNews(savedStateHandle.get("language") as? String?)) {
            fetchNewsByLanguage(savedStateHandle.get("language"))
        } else if (checkIfValidArgNews(savedStateHandle.get("source") as? String?)) {
            fetchNewsBySource(savedStateHandle.get("source"))
        } else {
            fetchNewsWithoutFilter()
        }
    }

    private fun fetchNewsWithoutFilter() {
        viewModelScope.launch {
            pager.flow.cachedIn(viewModelScope)
                .map {
                    it.filter { article ->
                        article.title?.isNotEmpty() == true &&
                                article.urlToImage?.isNotEmpty() == true
                    }
                }
                .collect {

                    logger.d("MY_TAG", "PagingData collected${it}")
                    _newsItemPaging.value = it
                }
        }
    }

    private fun fetchNewsByCountry(countryId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsByCountry(
                countryId ?: Const.DEFAULT_COUNTRY,
                pageNumber = pageNum
            )
                .mapFilterCollectNews()
        }
    }

    private fun fetchNewsByLanguage(languageId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsByLanguage(
                languageId ?: Const.DEFAULT_LANGUAGE,
                pageNumber = pageNum
            )
                .mapFilterCollectNews()
        }
    }

    private fun fetchNewsBySource(sourceId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsBySource(sourceId ?: Const.DEFAULT_SOURCE, pageNumber = pageNum)
                .mapFilterCollectNews()
        }
    }

    private suspend fun Flow<List<Article>>.mapFilterCollectNews() {
        this.map { item ->
            item.apply {
                this.filter {
                    it.title?.isNotEmpty() == true &&
                            it.urlToImage?.isNotEmpty() == true
                }
            }
        }
            .flowOn(dispatcherProvider.io)
            .catch {
                _newsItem.emit(UIState.Failure(it))
            }
            .collect {
                _newsItem.emit(UIState.Success(it))
                logger.d("NewsViewModel", "Success")
            }
    }

}