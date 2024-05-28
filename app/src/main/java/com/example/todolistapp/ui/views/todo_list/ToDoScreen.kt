package com.example.todolistapp.ui.views.todo_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.ui.components.HeaderToDo
import com.example.todolistapp.ui.components.ItemTaskCompleted
import com.example.todolistapp.ui.components.ItemTaskPending
import com.example.todolistapp.ui.components.ScaffoldBasic
import com.example.todolistapp.ui.utils.ASpacerHeight
import com.example.todolistapp.ui.utils.Toast
import com.example.todolistapp.ui.utils.launchedCollect


@Composable
fun ToDoScreen(viewModel: ToDoViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsState()

    viewModel.event.launchedCollect {
        when (it) {
            ToDoEvent.GenericError -> {
                context.Toast("Error in obtaining data")
            }

            ToDoEvent.ServiceError -> {
                context.Toast("Error in obtaining data")
            }
        }
    }
    ScaffoldBasic(
        topBar = {
            HeaderToDo(
                completed = uiState.amountTasksCompleted,
                pending = uiState.amountPendingTasks
            )
        },
        isLoader = uiState.isLoader
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(
                        uiState.toDoListCompleted,
                        key = { task -> task.id }) { task -> ItemTaskCompleted(task) }

                }

            }
            ASpacerHeight(height = 16.dp)
            items(
                uiState.toDoListPending,
                key = { task -> task.id }) { task -> ItemTaskPending(task) }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewToDoList() {
    ToDoScreen()
}