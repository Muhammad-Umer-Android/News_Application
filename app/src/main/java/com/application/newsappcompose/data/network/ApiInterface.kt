package com.application.newsappcompose.data.network


import com.application.newsappcompose.common.utils.Const.DEFAULT_COUNTRY
import com.application.newsappcompose.common.utils.Const.DEFAULT_LANGUAGE
import com.application.newsappcompose.common.utils.Const.DEFAULT_PAGE_NUM
import com.application.newsappcompose.common.utils.Const.DEFAULT_QUERY_PAGE_SIZE
import com.application.newsappcompose.common.utils.Const.DEFAULT_SOURCE
import com.application.newsappcompose.data.model.News
import com.application.newsappcompose.data.model.Sources
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("top-headlines")
    suspend fun getNewsByLang(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("top-headlines")
    suspend fun getNewsBySource(
        @Query("sources") sources: String = DEFAULT_SOURCE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("sources")
    suspend fun getSources(): Sources
}