package com.application.newsappcompose.common.logger

import android.util.Log

class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}