package academy.apparchitects.todoapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SerializableTodo(
  val id: String,
  val title: String? = null,
  val priority: String? = null,
  val description: String? = null,
  val isComplete: Boolean = false
) : Parcelable