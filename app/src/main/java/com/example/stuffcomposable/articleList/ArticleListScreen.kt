package com.example.stuffcomposable.articleList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.stuffcomposable.data.models.ArticleListEntry
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel
) {
    Surface(
	color = MaterialTheme.colors.background,
	modifier = Modifier.fillMaxSize()
    ) {
	Column {
	    SearchBar(
		hint = "Search...",
		modifier = Modifier
		    .fillMaxWidth()
		    .padding(16.dp)
	    ) {
	    }
	    ArticleList(
		navController = navController,
		viewModel = viewModel
	    )
	}
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
	mutableStateOf("")
    }
    var isHintDisplayed by remember {
	mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
	BasicTextField(
	    value = text,
	    onValueChange = {
		text = it
		onSearch(it)
	    },
	    maxLines = 1,
	    singleLine = true,
	    textStyle = TextStyle(color = Color.Black),
	    modifier = Modifier
		.background(Color.White)
		.fillMaxWidth()
		.padding(
		    horizontal = 20.dp,
		    vertical = 12.dp
		)
		.onFocusChanged {
		    isHintDisplayed = !it.isFocused
		}
	)
	if (isHintDisplayed) {
	    Text(
		text = hint,
		color = Color.LightGray,
		modifier = Modifier.padding(
		    horizontal = 20.dp,
		    vertical = 12.dp
		)
	    )
	}
    }
}

@Composable
fun ArticleList(
    navController: NavController,
    viewModel: ArticleListViewModel
) {
    val articleList by remember { viewModel.articleList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn {
	items(articleList.size) {
	    ArticleRow(
		rowIndex = it,
		entries = articleList,
		navController = navController,
	    )
	}
    }
    Box(
	contentAlignment = Alignment.Center,
	modifier = Modifier.fillMaxSize()
    ) {
	if (isLoading) {
	    CircularProgressIndicator(color = MaterialTheme.colors.primary)
	}
	if (loadError.isNotEmpty()) {
	    RetrySection(error = loadError) {
		viewModel.loadArticles()
	    }
	}
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
	Text(error, color = Color.Red, fontSize = 18.sp)
	Spacer(modifier = Modifier.height(8.dp))
	Button(
	    onClick = { onRetry() },
	    modifier = Modifier.align(CenterHorizontally)
	) {
	    Text(text = "Retry")
	}
    }
}

@Composable
fun ArticleEntry(
    entry: ArticleListEntry,
    navController: NavController) {
    Box(
	modifier = Modifier
	    .shadow(5.dp, RoundedCornerShape(10.dp))
	    .clip(RoundedCornerShape(10.dp))
	    .background(Color.White)
	    .aspectRatio(1f)
	    .clickable {
		val encodedUrl = URLEncoder.encode(entry.url, "UTF-8")
		navController.navigate(
		    "article_detail_screen/${encodedUrl}"
		)
	    }
    ) {
	Column {
	    AsyncImage(
		model = ImageRequest.Builder(LocalContext.current)
		    .data(entry.imageUrl)
		    .crossfade(true)
		    .build(),
		contentDescription = entry.title,
		modifier = Modifier
		    .fillMaxWidth()
	    ) //below everything repeats so needs to be a single compose unit
	    Spacer(modifier = Modifier.height(8.dp))
	    Text(
		text = entry.sectionName,
		modifier = Modifier
		    .padding(horizontal = 12.dp)
		    .fillMaxWidth(),
		color = MaterialTheme.colors.primaryVariant,
		fontSize = 10.sp
	    )
	    Spacer(modifier = Modifier.height(8.dp))
	    Text(
		text = entry.title,
		modifier = Modifier
		    .padding(horizontal = 12.dp)
		    .fillMaxWidth(),
		color = Color.Black,
		fontSize = 24.sp
	    )
	    Spacer(modifier = Modifier.height(8.dp))
	    Text(
		text = entry.body,
		modifier = Modifier
		    .padding(horizontal = 12.dp)
		    .fillMaxWidth(),
		color = Color.LightGray,
		fontSize = 16.sp
	    )
	}
    }
}

@Composable
fun ArticleRow(
    rowIndex: Int,
    entries: List<ArticleListEntry>,
    navController: NavController,
) {
    Column {
	ArticleEntry(
	    entry = entries[rowIndex],
	    navController = navController,
	)
    }
    Spacer(modifier = Modifier.height(16.dp))
}
