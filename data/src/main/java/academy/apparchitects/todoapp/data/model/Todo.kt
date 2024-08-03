package academy.apparchitects.todoapp.data.model

import kotlinx.datetime.Instant

data class Todo (
    val id: String,
    val title: String,
    val dueDate: Instant,
    val priority: String,
    val categoryId: String,
    val description: String ? = null,
    val isComplete: Boolean = false,
)