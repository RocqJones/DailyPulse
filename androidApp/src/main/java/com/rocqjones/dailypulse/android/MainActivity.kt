package com.rocqjones.dailypulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rocqjones.dailypulse.Platform
import com.rocqjones.dailypulse.articles.ArticlesViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SystemLog
        Platform().logSystemInfo()

        val articlesViewModel : ArticlesViewModel by viewModels()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     //AboutScreen()
                    //ArticlesScreen(articlesViewModel = articlesViewModel)
                    AppScaffold(articlesViewModel = articlesViewModel)
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        AboutScreen()
    }
}
*/
