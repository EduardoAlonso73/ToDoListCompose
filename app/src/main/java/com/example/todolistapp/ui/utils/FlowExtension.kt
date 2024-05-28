package com.example.todolistapp.ui.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("ComposableNaming")
@Composable
fun <T> SharedFlow<T>.launchedCollect(onNewValue: suspend (T) -> Unit) {
    LaunchedEffect(Unit) {
        collectLatest(onNewValue)
    }
}