package com.application.newsappcompose.data.database.entity

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)