package academy.apparchitects.todoapp.presentation.todoaddedit

import academy.apparchitects.todoapp.data.model.Todo
import academy.apparchitects.todoapp.presentation.tododetails.TaskDetailsStates
import kotlinx.datetime.Instant

sealed class AddEditTaskStates {

    data class AddTask(
        val id: String = "",
        val title: String = "",
        val dueDate: Instant? = null,
        val priority: String = "",
        val categoryId: String = "",
        val description: String ? = null,
    ) : AddEditTaskStates()

    data class Success(
        val task: Todo
    ) : AddEditTaskStates()

}