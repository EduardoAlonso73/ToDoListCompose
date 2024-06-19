package com.example.todolistapp.ui.views.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoUseCase
import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.getResult
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
            response.getResult(
                onSuccess = { successHandling(it) },
                onError = { errorHandling(it) }
            )

        }.invokeOnCompletion {
            _state.update { it.copy(isLoader = false) }
        }
    }

    private fun successHandling(data: List<GetToDoEntity>?) {
        val amountTasksCompleted = data.orEmpty().count { it.completed }
        val amountPendingTasks = data.orEmpty().count { !it.completed }
        return _state.update {
            it.copy(
                toDoListPending = data.orEmpty().filter { task -> !task.completed },
                toDoListCompleted = data.orEmpty().filter { task -> task.completed },
                amountTasksCompleted = "$amountTasksCompleted",
                amountPendingTasks = "$amountPendingTasks",
            )
        }
    }

    private suspend fun errorHandling(errorEntity: ErrorEntity.Network?) {
        errorEntity?.let {
            return _event.emit(
                when (it) {
                    is ErrorEntity.Network.ServiceError -> ToDoEvent.ServiceError
                    else -> ToDoEvent.GenericError

                }
            )
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