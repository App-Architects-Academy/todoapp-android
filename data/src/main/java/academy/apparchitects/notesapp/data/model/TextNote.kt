package academy.apparchitects.notesapp.data.model

import kotlinx.datetime.Instant
import java.util.UUID

data class TextNote(
    override val id: UUID,
    val title: String? = null,
    val desc: String? = null,
    val note: String,
    val createdOn: Instant,
    val editedOn: Instant? = null,
) : Note {
    override fun toSerializable(): SerializableNote  {
        return SerializableNote(
            id = id.toString(),
            title = title,
            desc = desc,
            note = note,
            type = NoteType.Text
        )
    }
}


