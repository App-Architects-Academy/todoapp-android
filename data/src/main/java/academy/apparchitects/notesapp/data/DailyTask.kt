package academy.apparchitects.notesapp.data

import kotlinx.datetime.Instant
import java.util.UUID


/**
 * Written by Vikram Ragu on 19/06/24.
 */
data class DailyTask(
    val id: UUID,
    val title: String? = null,
    val todoItems : List<TodoItem> = emptyList(),
    val createdOn: Instant,
    val editedOn: Instant? = null,
)

data class TodoItem(
    val isCompleted : Boolean = false,
    val item : String
)