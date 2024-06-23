package academy.apparchitects.notesapp.data.model

import java.util.UUID

sealed interface Note {
    val id: UUID

    fun toSerializable(): SerializableNote
}