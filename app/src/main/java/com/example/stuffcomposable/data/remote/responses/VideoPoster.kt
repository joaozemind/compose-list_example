package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class VideoPoster(
    @Expose
    val height: String = "",
    @Expose
    val id: Int = 0,
    @Expose
    val layout: String = "",
    @Expose
    val src: String = "",
    @Expose
    val width: String = ""
)