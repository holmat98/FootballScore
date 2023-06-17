package com.mateuszholik.uicomponents.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Article
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.extensions.asFullDateTimeString
import com.mateuszholik.uicomponents.images.Image
import com.mateuszholik.uicomponents.texts.SmallText

@Composable
fun NewsDetails(
    article: Article,
    onReadMoreClicked: (url: String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    LazyColumn(modifier = modifier) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
                    .clip(RoundedCornerShape(MaterialTheme.cornerRadius.large)),
                url = article.urlToImage,
                onErrorImageRes = R.drawable.football_news
            )
        }

        item {
            Text(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small
                ),
                text = article.title,
                color = textColor,
                fontSize = MaterialTheme.textSizing.normal,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)) {
                Icon(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
                SmallText(
                    text = "${article.author} · ${article.publishedAt.asFullDateTimeString()}",
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        item { CustomDivider() }

        if (article.description.isNotEmpty() || article.content.isNotEmpty()) {
            item {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.small),
                    text = article.content.ifEmpty { article.description },
                    color = textColor,
                    fontSize = MaterialTheme.textSizing.normal,
                )
            }
        }

        item {
            Text(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small,
                    )
                    .clickable { onReadMoreClicked(article.url) },
                text = stringResource(R.string.read_more),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = MaterialTheme.textSizing.normal,
            )
        }
    }
}
