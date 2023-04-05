package com.mateuszholik.uicomponents.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun RoundedColumnTextCard(
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = MaterialTheme.textSizing.normal,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    cornerRadius: Dp = MaterialTheme.cornerRadius.normal,
    innerPadding: Dp = MaterialTheme.spacing.small,
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {

        val constraintSet = createConstraintSet(innerPadding)

        ConstraintLayout(constraintSet) {
            Text(
                modifier = Modifier.layoutId(TOP_TEXT_ID),
                text = topText,
                fontSize = textSize
            )
            Box(
                modifier = Modifier
                    .height(DividerDefaults.Thickness)
                    .layoutId(DIVIDER_ID)
                    .background(color = contentColor)
            )
            Text(
                modifier = Modifier.layoutId(BOTTOM_TEXT_ID),
                text = bottomText,
                fontSize = textSize
            )
        }
    }
}

private fun createConstraintSet(margin: Dp): ConstraintSet =
    ConstraintSet {
        val topTextRef = createRefFor(TOP_TEXT_ID)
        val dividerRef = createRefFor(DIVIDER_ID)
        val bottomTextRef = createRefFor(BOTTOM_TEXT_ID)

        constrain(topTextRef) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
        }

        constrain(dividerRef) {
            top.linkTo(topTextRef.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
            width = Dimension.fillToConstraints
        }

        constrain(bottomTextRef) {
            top.linkTo(dividerRef.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
            bottom.linkTo(parent.bottom, margin = margin)
        }
    }

private const val TOP_TEXT_ID = "topText"
private const val DIVIDER_ID = "divider"
private const val BOTTOM_TEXT_ID = "bottomText"

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        RoundedColumnTextCard(
            modifier = Modifier,
            topText = "10",
            bottomText = "5"
        )
    }
}
