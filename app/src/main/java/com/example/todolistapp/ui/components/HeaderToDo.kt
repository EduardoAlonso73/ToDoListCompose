package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.utils.ASpacer
import com.example.todolistapp.ui.utils.ASpacerWidth
import com.example.todolistapp.ui.utils.boldTextStyle
import com.example.todolistapp.ui.utils.subTextStyle

@Composable
fun HeaderToDo(completed: String, pending: String) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "You Task", style = boldTextStyle)
        ASpacer(height = 8.dp)
        Text(text = "Completed :$completed", style = subTextStyle)
        Text(text = "Pending :$pending", style = subTextStyle)
    }

}
