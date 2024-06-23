package academy.apparchitects.notesapp.data.model

import kotlinx.datetime.Instant
import java.util.UUID


/**
 * Written by Vikram Ragu on 19/06/24.
 */
data class DailyTask(
    override val id: UUID,
    val title: String? = null,
    val todoItems : List<TodoItem> = emptyList(),
    val createdOn: Instant,
    val editedOn: Instant? = null,
) : Note {
    override fun toSerializable(): SerializableNote = SerializableNote(
        id = id.toString(),
        title = title,
        note = todoItems.joinToString(separator = "\n"),
        type = NoteType.Task
    )
}

data class TodoItem(
    val isCompleted : Boolean = false,
    val item : String
)