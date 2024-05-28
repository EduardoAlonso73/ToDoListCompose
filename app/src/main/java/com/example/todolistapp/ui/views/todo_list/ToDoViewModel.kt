package com.example.todolistapp.ui.views.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoUseCase
import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.ui.utils.Constants.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val getToDo: GetToDoUseCase) : ViewModel() {
    private val _state = MutableStateFlow(ToDoUIState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ToDoEvent>()
    val event = _event.asSharedFlow()

    init {
        getToDoList()
    }


    private fun getToDoList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoader = true) }
            val response = getToDo.invoke()
            response.error?.let {
                return@launch _event.emit(
                    when (it) {
                        is ErrorEntity.ServiceError -> ToDoEvent.ServiceError
                        else -> ToDoEvent.GenericError

                    }
                )
            }

            val amountTasksCompleted = response.data.orEmpty().count { it.completed }
            val amountPendingTasks = response.data.orEmpty().count { !it.completed }
            return@launch _state.update {
                it.copy(
                    toDoListPending = response.data.orEmpty().filter { task -> !task.completed },
                    toDoListCompleted = response.data.orEmpty().filter { task -> task.completed },
                    amountTasksCompleted = "$amountTasksCompleted",
                    amountPendingTasks = "$amountPendingTasks",
                )
            }


        }.invokeOnCompletion {
            _state.update { it.copy(isLoader = false) }
        }
    }
}


data class ToDoUIState(
    val isLoader: Boolean = false,
    val toDoListPending: List<GetToDoEntity> = emptyList(),
    val toDoListCompleted: List<GetToDoEntity> = emptyList(),
    val amountTasksCompleted: String = emptyString,
    val amountPendingTasks: String = emptyString
)

sealed interface ToDoEvent {
    data object ServiceError : ToDoEvent
    data object GenericError : ToDoEvent
}