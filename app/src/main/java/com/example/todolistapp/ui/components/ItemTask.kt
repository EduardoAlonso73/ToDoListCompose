package com.example.todolistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.ui.utils.ASpacer

@Composable
fun ItemTaskPending(task: GetToDoEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight()
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = task.id.toString())
            Icon(
                modifier = Modifier.alpha(if (task.completed) 1f else 0f),
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null, tint = Color(
                    0xFF51C556
                )
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = task.title.toString()
        )
        Spacer(modifier = Modifier.height(8.dp))

    }

}

@Composable
fun ItemTaskCompleted(task: GetToDoEntity) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp)
            .padding(8.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = task.id.toString())
            Icon(
                modifier = Modifier.alpha(if (task.completed) 1f else 0f),
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null, tint = Color(
                    0xFF51C556
                )
            )
        }
        ASpacer(8.dp)
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = task.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))

    }

}
