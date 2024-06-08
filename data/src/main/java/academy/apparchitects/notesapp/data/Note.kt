package academy.apparchitects.notesapp.data

import kotlinx.datetime.Instant
import java.util.UUID

data class Note(
    val id: UUID,
    val title: String? = null,
    val note: String,
    val createdOn: Instant,
    val editedOn: Instant? = null,
)
