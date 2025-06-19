package com.application.newsappcompose.common.utils

object ValidationUtil {

    fun checkIfValidArgNews(str: String?): Boolean {
        return !(str.isNullOrEmpty() || str == "{country}" || str == "{language}" || str == "{source}")
    }

}