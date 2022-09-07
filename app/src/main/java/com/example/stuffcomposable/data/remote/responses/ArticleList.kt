package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleList(
    @Expose
    @SerializedName("assets")
    val articles: List<Article> = listOf(),
    @Expose
    val generated: String = ""
)