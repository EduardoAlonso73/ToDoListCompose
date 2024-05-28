package com.example.todolistapp.ui.utils


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.ASpacer(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun ColumnScope.ASpacer(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}

@Composable
fun ASpacerWidth(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun ASpacerHeight(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}


fun LazyListScope.ASpacerWidth(width: Dp, modifier: Modifier = Modifier) {
    item { Spacer(modifier = modifier.width(width)) }

}


fun LazyListScope.ASpacerHeight(height: Dp, modifier: Modifier = Modifier) {
    item { Spacer(modifier = modifier.height(height)) }
}

@Composable
fun LazyItemScope.ASpacer(padding: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.size(padding))
}