package com.instadivassignment

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun TagCloudScreen(navController: NavController, viewModel: TagCloudViewModel = viewModel()) {
    val tagList by viewModel.tagList.collectAsState(initial = emptyList())
    var selectedTag by remember { mutableStateOf<String?>(null) }

    Log.d("TagCloud", "Current tag list: $tagList") // Log to verify tag list content

    // Ensure a scrollable container so all tags can be seen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tag Cloud
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // To ensure grid takes up space above the button
            columns = GridCells.Adaptive(minSize = 100.dp), // Change the grid cell size based on content
            contentPadding = PaddingValues(8.dp)
        ) {
            items(tagList.size) { index ->
                val tag = tagList[index]
                Log.d("TagCloud", "Rendering tag: $tag") // Log each tag rendered

                Chip(
                    tag = tag,
                    isSelected = tag == selectedTag,
                    onSelected = {
                        Log.d("TagCloud", "Tag clicked: $tag") // Log the clicked tag
                        selectedTag = tag
                        Log.d("TagCloud", "Tag selected: $selectedTag") // Log the updated selected tag
                    }
                )
            }
        }

        // Submit Button
        Button(
            onClick = {
                selectedTag?.let {
                    Log.d("TagCloud", "Navigating with selected tag: $it") // Log navigation with tag
                    navController.navigate("nextScreen/$it")
                } ?: Log.d("TagCloud", "No tag selected yet!") // Log when no tag is selected
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun Chip(tag: String, isSelected: Boolean, onSelected: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onSelected() }
            .wrapContentWidth(), // Ensure the width wraps to the content
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.DarkGray
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(8.dp),
            color = Color.White
        )
    }
}
