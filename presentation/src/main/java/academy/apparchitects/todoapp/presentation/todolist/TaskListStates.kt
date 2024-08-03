package academy.apparchitects.todoapp.presentation.todolist

import academy.apparchitects.todoapp.data.model.DailyTask
import academy.apparchitects.todoapp.data.model.Reminder
import academy.apparchitects.todoapp.data.model.TextNote
import academy.apparchitects.todoapp.data.model.Todo

sealed class TaskListStates {

    data class Success(
        val tasks: List<Todo>,
        val isRefreshing: Boolean = false
    ) : TaskListStates()

    data class Error(
        val errorDetails: String
    ) : TaskListStates()

    object Loading : TaskListStates()


    object Idle : TaskListStates()

    object Empty : TaskListStates()
}