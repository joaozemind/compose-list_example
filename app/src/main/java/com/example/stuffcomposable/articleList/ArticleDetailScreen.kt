package com.example.stuffcomposable.articleList

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController


@Composable
fun ArticleDetailScreen(
    navController: NavController,
    articleUrl: String?
) {
    var backEnabled by remember { mutableStateOf(false) }
    val url = remember { mutableStateOf(articleUrl?.replaceFirst("http://", "https://")?.replaceFirst("_json/", "")) }
    var webView: WebView? = null



    AndroidView(
	factory = { context ->
	    WebView(context).apply {
		layoutParams = ViewGroup.LayoutParams(
		    ViewGroup.LayoutParams.MATCH_PARENT,
		    ViewGroup.LayoutParams.MATCH_PARENT
		)
		webViewClient = object : WebViewClient() {
		    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
			backEnabled = view.canGoBack()
		    }

		    override fun onPageFinished(view: WebView?, url: String?) {
			super.onPageFinished(view, url)
		    }
		}
		settings.javaScriptEnabled = true
		url.value?.let { loadUrl(it) }
		webView = this
	    }
	}, update = {
	    webView = it
	})

    BackHandler(enabled = backEnabled) {
	webView?.goBack()
    }
}
