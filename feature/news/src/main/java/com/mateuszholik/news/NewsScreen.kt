package com.mateuszholik.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.Article
import com.mateuszholik.model.Source
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.news.NewsItem
import com.mateuszholik.uicomponents.news.NewsItemHeader
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)

    val topSportNewsUiState by viewModel.topSportsNewsUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.news_title))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            when (topSportNewsUiState) {
                is UiState.Loading -> Loading()
                is UiState.Success -> Content(
                    data = (topSportNewsUiState as UiState.Success<List<Article>>).data,
                    paddingValues = paddingValues
                )
                is UiState.Error ->
                    ErrorInfo((topSportNewsUiState as UiState.Error<List<Article>>).errorType)
            }
        }
    )
}

@Composable
private fun Content(
    data: List<Article>,
    paddingValues: PaddingValues,
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(items = data) { index, article ->
            if (index == 0) {
                NewsItemHeader(article = article)
            } else {
                NewsItem(article = article)
            }

            CustomDivider()
        }
    }
}

@Preview
@Composable
fun Preview() {
    FootballScoreTheme {
        Surface {
            Content(
                data = listOf(
                    Article(
                        author = "Author",
                        content = "Content",
                        description = "Description",
                        publishedAt = LocalDateTime.of(2023, 6, 15, 12, 30, 0),
                        source = Source("id", "sourceName"),
                        title = "Title",
                        urlToImage = "",
                        url = ""
                    )
                ),
                paddingValues = PaddingValues(0.dp)
            )
        }
    }
}
