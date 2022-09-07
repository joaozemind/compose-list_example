package com.example.stuffcomposable.articleList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffcomposable.data.models.ArticleListEntry
import com.example.stuffcomposable.repository.StuffRepository
import com.example.stuffcomposable.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val repository: StuffRepository
) : ViewModel() {
    var articleList = mutableStateOf<List<ArticleListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
	loadArticles()
    }

    fun loadArticles() {
	viewModelScope.launch {
	    isLoading.value = true
	    val result = repository.getArticleList()
	    when (result) {
		is Resource.Success -> {
		    val articleEntries = result.data?.articles?.mapIndexed { index, entry ->
			var imageUrl = ""
			entry.images.mapIndexed { _, image ->
			    image.variants.forEach {
				if (it.layout.contentEquals("Standard Image")) {
				    imageUrl = it.src
				}
			    }
			}
			ArticleListEntry(
			    url = entry.url,
			    title = entry.title,
			    imageUrl = imageUrl,
			    body = entry.altIntro,
			    sectionName = entry.section,
			    updatedAt = entry.datetimeDisplay
			)
		    }
		    if (articleEntries != null) {
			articleList.value = articleEntries
		    } else {
			loadError.value = "Empty list"
		    }
		    isLoading.value = false
		}

		is Resource.Error   -> {
		    loadError.value = result.message ?: ""
		    isLoading.value = false

		}

		is Resource.Loading -> {} //do nothing for now
	    }
	}
    }

}