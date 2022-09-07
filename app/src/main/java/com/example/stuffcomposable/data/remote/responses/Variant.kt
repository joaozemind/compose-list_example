package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Variant(
    @Expose
    val height: String = "",
    @Expose
    val id: Int = 0,
    @SerializedName("image_type_id")
    @Expose
    val imageTypeId: String = "",
    @Expose
    val layout: String = "",
    @SerializedName("media_type")
    @Expose
    val mediaType: String = "",
    @Expose
    val src: String = "",
    @Expose
    val urls: Urls = Urls(),
    @Expose
    val width: String = ""
)