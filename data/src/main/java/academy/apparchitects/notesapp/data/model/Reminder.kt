package academy.apparchitects.notesapp.data.model

import kotlinx.datetime.Instant
import java.util.UUID

data class Reminder(
    override val id: UUID,
    val reminder: String,
    val isCompleted: Boolean = false,
    val createdOn: Instant,
    val editedOn: Instant? = null,
) : Note {
    override fun toSerializable(): SerializableNote = SerializableNote(
        id = id.toString(),
        title = reminder,
        note = if (isCompleted) "This reminder is completed" else "This reinder is yet to be completed",
        type = NoteType.Reminder
    )
}


