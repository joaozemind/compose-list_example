package com.example.stuffcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stuffcomposable.articleList.ArticleDetailScreen
import com.example.stuffcomposable.articleList.ArticleListScreen
import com.example.stuffcomposable.articleList.ArticleListViewModel
import com.example.stuffcomposable.ui.theme.StuffComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	setContent {
	    StuffComposeTheme {
		val navController = rememberNavController()
		NavHost(
		    navController = navController,
		    startDestination = "article_list_screen"
		) {
		    composable("article_list_screen") {
			val viewModel = hiltViewModel<ArticleListViewModel>()
			ArticleListScreen(navController = navController, viewModel = viewModel)
		    }

		    composable(
			"article_detail_screen/{articleUrl}",
			arguments = listOf(navArgument("articleUrl") {
			    type = NavType.StringType
			})
		    ) {
			val articleUrl = remember {
			    it.arguments?.getString("articleUrl")
			}
			ArticleDetailScreen(navController = navController, articleUrl = articleUrl)
		    }
		}
	    }
	}
    }


}