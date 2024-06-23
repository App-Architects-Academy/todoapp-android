package academy.apparchitects.notesapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SerializableNote(
  val id: String,
  val type: NoteType,
  val title: String? = null,
  val desc: String? = null,
  val note: String? = null
) : Parcelable

enum class NoteType {
  Text, Task, Reminder
}