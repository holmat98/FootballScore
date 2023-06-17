package com.mateuszholik.uicomponents.news

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Article
import com.mateuszholik.model.Source
import com.mateuszholik.uicomponents.images.Image
import java.time.LocalDateTime

@Composable
fun NewsItem(
    article: Article,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .weight(3f)
                .padding(MaterialTheme.spacing.small)
                .clip(RoundedCornerShape(MaterialTheme.cornerRadius.normal)),
            url = article.urlToImage,
            onErrorImageRes = R.drawable.football_news
        )
        Text(
            modifier = Modifier
                .weight(7f)
                .padding(MaterialTheme.spacing.small),
            text = article.title,
            color = textColor,
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface {
            NewsItem(
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
