package com.rocqjones.dailypulse.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rocqjones.dailypulse.Platform

@Composable
fun AboutScreen() {
    Column {
        Toolbar()
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(
        title = { Text(text = "About Device") }
    )
}

@Composable
private fun ContentView() {
    val listItems = makeItems()
    
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listItems) { row ->
            RowView(title = row.first, subTitle = row.second)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    // creates list from platform
    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", "${platform.density}")
    )
}

@Composable
private fun RowView(title: String, subTitle: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodyLarge
        )

        Divider()
    }
}