package com.mateuszholik.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.Article
import com.mateuszholik.model.ArticleSortingOptions
import com.mateuszholik.model.Source
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.bottomsheetdialog.BottomSheetDialog
import com.mateuszholik.uicomponents.buttons.SelectableButton
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.news.NewsDetails
import com.mateuszholik.uicomponents.news.NewsItem
import com.mateuszholik.uicomponents.news.NewsItemHeader
import com.mateuszholik.uicomponents.scaffold.CustomScaffold
import java.time.LocalDateTime

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val sortingOption by viewModel.sortingOptions.collectAsStateWithLifecycle()
    val topSportNewsUiState by viewModel.topSportsNewsUiState.collectAsStateWithLifecycle()

    CustomScaffold(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.news_title)) }
    ) { paddingValues ->
        when (topSportNewsUiState) {
            is UiState.Loading -> Loading()
            is UiState.Success -> Content(
                currentSortingOption = sortingOption,
                data = (topSportNewsUiState as UiState.Success<List<Article>>).data,
                paddingValues = paddingValues,
                onSortingOptionClicked = { viewModel.changeSortingOption(it) }
            )
            is UiState.Error ->
                ErrorInfo((topSportNewsUiState as UiState.Error<List<Article>>).errorType)
        }

    }
}

@Composable
private fun Content(
    currentSortingOption: ArticleSortingOptions,
    data: List<Article>,
    paddingValues: PaddingValues,
    onSortingOptionClicked: (sortingOption: ArticleSortingOptions) -> Unit,
) {
    var chosenArticle by remember { mutableStateOf<Article?>(null) }
    var shouldShowBottomSheet by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        state = lazyListState
    ) {
        item {
            Text(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.small,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small
                ),
                text = stringResource(R.string.sort_by_title),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            LazyRow {
                items(items = ArticleSortingOptions.values().toList()) {
                    SelectableButton(
                        text = stringResource(it.toStringRes),
                        isSelected = currentSortingOption == it,
                        onClick = { onSortingOptionClicked(it) }
                    )
                }
            }
        }

        itemsIndexed(items = data) { index, article ->
            val modifier = Modifier.clickable {
                chosenArticle = article
                shouldShowBottomSheet = true
            }

            if (index == 0) {
                NewsItemHeader(
                    modifier = modifier,
                    article = article
                )
            } else {
                NewsItem(
                    modifier = modifier,
                    article = article
                )
            }

            if (index < data.lastIndex) {
                CustomDivider()
            }
        }
    }

    chosenArticle?.let {
        BottomSheetDialog(
            isVisible = shouldShowBottomSheet,
            onDialogClosed = {
                shouldShowBottomSheet = false
                chosenArticle = null
            }
        ) {
            NewsDetails(
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.normal),
                article = it,
                onReadMoreClicked = { urlToArticle ->
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        this.data = Uri.parse(urlToArticle)
                    }

                    context.startActivity(intent)
                }
            )
        }
    }
}

private val ArticleSortingOptions.toStringRes: Int
    get() = when (this) {
        ArticleSortingOptions.RELEVANCY -> R.string.article_sorting_option_relevancy
        ArticleSortingOptions.POPULARITY -> R.string.article_sorting_option_popularity
        ArticleSortingOptions.PUBLISHED_AT -> R.string.article_sorting_option_published_at
    }

@Preview(device = Devices.PIXEL_4)
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
                    ),
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
                paddingValues = PaddingValues(0.dp),
                currentSortingOption = ArticleSortingOptions.POPULARITY,
                onSortingOptionClicked = {}
            )
        }
    }
}
