package com.example.stuffcomposable.repository

import com.example.stuffcomposable.data.remote.StuffAPI
import com.example.stuffcomposable.data.remote.responses.Article
import com.example.stuffcomposable.data.remote.responses.ArticleList
import com.example.stuffcomposable.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class StuffRepository @Inject constructor(
	private val api: StuffAPI
) {
	suspend fun getArticleList(): Resource<ArticleList> {
		val response = try {
			api.getArticleList()
		} catch (e: Exception) {
			return Resource.Error("an Error Occurred")
		}
		return Resource.Success(response)
	}

	suspend fun getArticle(articleId: String): Resource<Article> {
		val response = try {
			api.getArticleById(articleId)
		} catch (e: Exception) {
			return Resource.Error("an Error Occurred")
		}
		return Resource.Success(response)
	}
}