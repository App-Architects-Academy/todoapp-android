package academy.apparchitects.notesapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SerializableNote(
  val id: String,
  val title: String? = null,
  val desc: String? = null,
  val note: String,
) : Parcelable