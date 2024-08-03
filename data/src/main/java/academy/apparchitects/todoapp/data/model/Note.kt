package academy.apparchitects.todoapp.data.model

import java.util.UUID

sealed interface Note {
    val id: UUID

    fun toSerializable(): SerializableNote
}