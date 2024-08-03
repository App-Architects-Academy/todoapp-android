package academy.apparchitects.todoapp.presentation.tododetails

import academy.apparchitects.todoapp.data.model.Todo
import academy.apparchitects.todoapp.presentation.todoaddedit.AddEditTaskStates

sealed class TaskDetailsStates {

    data class Success(
        val task: Todo
    ) : TaskDetailsStates()

    data class Error(
        val errorDetails: String
    ) : TaskDetailsStates()

    data class Loading(
        val taskId: String
    ) : TaskDetailsStates()

    object Idle : TaskDetailsStates()

}