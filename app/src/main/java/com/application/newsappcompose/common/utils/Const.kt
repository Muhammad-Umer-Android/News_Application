package com.application.newsappcompose.common.utils

import com.application.newsappcompose.data.model.Country
import com.application.newsappcompose.data.model.Language

object Const {

    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val DEFAULT_QUERY_PAGE_SIZE = 20
    const val DEFAULT_PAGE_NUM = 1
    const val DEFAULT_COUNTRY = "us"
    const val DEFAULT_LANGUAGE = "en"
    const val DEFAULT_SOURCE = "abc-news"
    const val API_KEY = "Use your own key"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val DB_NAME = "article_db"

    //WorkManager and Notification
    const val UNIQUE_WORK_NAME = "newsAppPeriodicWork"
    const val MORNING_UPDATE_TIME = 5
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "news_channel"
    const val NOTIFICATION_CHANNEL_NAME = "News"
    const val NOTIFICATION_CONTENT_TITLE = "News"
    const val NOTIFICATION_CONTENT_TEXT = "Check out the latest news ..."


    val countryList: List<Country> = listOf(
        Country("United Arab Emirates", "ae"),
        Country("Argentina", "ar"),
        Country("Austria", "at"),
        Country("Australia", "au"),
        Country("Belgium", "be"),
        Country("Bulgaria", "bg"),
        Country("Brazil", "br"),
        Country("Canada", "ca"),
        Country("Switzerland", "ch"),
        Country("China", "cn"),
        Country("Colombia", "co"),
        Country("Cuba", "cu"),
        Country("Czech Republic", "cz"),
        Country("Germany", "de"),
        Country("Egypt", "eg"),
        Country("France", "fr"),
        Country("United Kingdom", "gb"),
        Country("Greece", "gr"),
        Country("Hong Kong", "hk"),
        Country("Hungary", "hu"),
        Country("Indonesia", "id"),
        Country("Ireland", "ie"),
        Country("Israel", "il"),
        Country("India", "in"),
        Country("Italy", "it"),
        Country("Japan", "jp"),
        Country("South Korea", "kr"),
        Country("Lithuania", "lt"),
        Country("Latvia", "lv"),
        Country("Morocco", "ma"),
        Country("Mexico", "mx"),
        Country("Malaysia", "my"),
        Country("Nigeria", "ng"),
        Country("Netherlands", "nl"),
        Country("Norway", "no"),
        Country("New Zealand", "nz"),
        Country("Philippines", "ph"),
        Country("Poland", "pl"),
        Country("Portugal", "pt"),
        Country("Romania", "ro"),
        Country("Serbia", "rs"),
        Country("Russia", "ru"),
        Country("Saudi Arabia", "sa"),
        Country("Sweden", "se"),
        Country("Singapore", "sg"),
        Country("Slovakia", "sk"),
        Country("Thailand", "th"),
        Country("Turkey", "tr"),
        Country("Taiwan", "tw"),
        Country("Ukraine", "ua"),
        Country("United States", "us"), //A
        Country("Venezuela", "ve"),
        Country("South Africa", "za")
    )
    val languageList = listOf(
        Language("Arabic", "ar"),
        Language("German", "de"),
        Language("English", "en"),
        Language("Spanish", "es"),
        Language("French", "fr"),
        Language("Hebrew", "he"),
        Language("Italian", "it"),
        Language("Dutch", "nl"),
        Language("Norwegian", "no"),
        Language("Portuguese", "pt"),
        Language("Russian", "ru"),
        Language("Swedish", "sv"),
        Language("Chinese", "zh")
    )

}