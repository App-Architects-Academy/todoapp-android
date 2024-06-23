package academy.apparchitects.notesapp.presentation.notedetails

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.UUID

sealed interface AddNoteState {
  data object Loading : AddNoteState

  data class Init(
    val noteId: UUID? = null,
    val title: String? = null,
    val desc: String? = null,
    val note: String? = null,
    val createdOn: Instant = Clock.System.now(),
  ) : AddNoteState

  data class Error(
    val errorMsg: String
  ) : AddNoteState

  object Success: AddNoteState
}

data class AddTextNote(
  val title: String,
  val desc: String,
  val note: String
)