package academy.apparchitects.notesapp.data.model

import java.util.UUID

data class Reminder(
    override val id: UUID,
    val reminder: String,
    val isCompleted: Boolean = false
) : Note {
    override fun toSerializable(): SerializableNote = SerializableNote(
        id = id.toString(),
        title = reminder,
        note = if (isCompleted) "This reminder is completed" else "This reinder is yet to be completed",
        type = NoteType.Reminder
    )
}


