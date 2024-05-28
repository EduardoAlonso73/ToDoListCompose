package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScaffoldBasic(
    topBar: @Composable () -> Unit = {},
    isLoader: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    if (isLoader) {
        LoaderPager()
        return
    }
    Scaffold(
        topBar = topBar,
        containerColor = Color(0xFFF2F2F2)
    ) {
        content(it)
    }
}