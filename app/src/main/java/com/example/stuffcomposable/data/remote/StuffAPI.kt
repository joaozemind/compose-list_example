package com.example.stuffcomposable.data.remote

import com.example.stuffcomposable.data.remote.responses.Article
import com.example.stuffcomposable.data.remote.responses.ArticleList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StuffAPI {

	@GET("c26421ab0906bb13adb7")
	suspend fun getArticleList(): ArticleList

	@GET("{articleId}")
	suspend fun getArticleById(
		@Path("articleId") articleId : String
	): Article

}