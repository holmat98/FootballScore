package com.mateuszholik.footballscore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.matches.MatchesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballScoreTheme(dynamicColors = false) {
                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.primary,
                    isNavigationBarContrastEnforced = true
                )

                MatchesScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        color = MaterialTheme.colorScheme.onPrimary,
        text = "Hello $name!"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FootballScoreTheme {
        Greeting("Android")
    }
}
