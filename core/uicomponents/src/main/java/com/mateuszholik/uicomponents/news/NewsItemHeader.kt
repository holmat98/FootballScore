package com.mateuszholik.uicomponents.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Article
import com.mateuszholik.model.Source
import com.mateuszholik.uicomponents.images.Image
import java.time.LocalDateTime

@Composable
fun NewsItemHeader(
    article: Article,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
                .clip(RoundedCornerShape(MaterialTheme.cornerRadius.medium)),
            url = article.urlToImage,
            onErrorImageRes = R.drawable.football_news
        )
        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.normal
            ),
            text = article.title,
            color = textColor,
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface {
            NewsItemHeader(
                article = Article(
                    author = "Author",
                    content = "Content",
                    description = "Description",
                    publishedAt = LocalDateTime.of(2023, 6, 15, 12, 30, 0),
                    source = Source("id", "sourceName"),
                    title = "Title",
                    urlToImage = "",
                    url = ""
                )
            )
        }
    }
}
