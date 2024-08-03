package academy.apparchitects.todoapp.presentation.notedetails

import academy.apparchitects.todoapp.data.NotesRepository
import academy.apparchitects.todoapp.data.model.SerializableNote
import academy.apparchitects.todoapp.data.model.TextNote
import academy.apparchitects.todoapp.presentation.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val noteRepo: NotesRepository
) : BaseViewModel<NoteDetailsState>() {

    private val _state: MutableStateFlow<NoteDetailsState> =
        MutableStateFlow(NoteDetailsState.Loading)
    override val state: StateFlow<NoteDetailsState> = _state

    private val _addState: MutableStateFlow<AddNoteState> =
        MutableStateFlow(AddNoteState.Init())
    val addState: StateFlow<AddNoteState> = _addState

    fun updateTitle(newTitle: String) {
        _state.update {
            updateSuccessState(it) { success ->
                success.copy(title = newTitle)
            }
        }
    }

    fun updateDescription(newDesc: String) {
        _state.update {
            updateSuccessState(it) { success ->
                success.copy(desc = newDesc)
            }
        }
    }

    fun updateContent(content: String) {
        _state.update {
            updateSuccessState(it) { success ->
                success.copy(note = content)
            }
        }
    }

    fun initialUiState(
        id: String?,
        // TODO: This is hacky for now to pass the note object to the details screen
        // Change it once repository layer is there
        note: SerializableNote?
    ) {
        if (id != null) {
            // Existing note's details
            viewModelScope.launch {
//        try {
//          val note = noteRepo.getNoteById(id)
//          _state.update {
//            NoteDetailsState.Success(
//              noteId = note.id,
//              title = note.title,
//              note = note.note
//            )
//          }
//        } catch (e: Exception) {
//          _state.update {
//            NoteDetailsState.Error("Failed to fetch note: ${e.message}")
//          }
//        }
            }
            // TODO: This is hacky for now to pass the note object to the details screen
            // Change it once repository layer is there
            note?.let {
                _state.update {
                    NoteDetailsState.Success(
                        noteId = UUID.fromString(note.id),
                        title = note.title,
                        desc = note.desc,
                        note = note.note
                    )
                }
            }
        } else {
            // New note
            _state.update {
                NoteDetailsState.Success(
                    noteId = null,
                    title = "",
                    desc = "",
                    note = ""
                )
            }
        }
    }


    fun addOrUpdateNoteInDb() {
        if (_state.value is NoteDetailsState.Success) {
            val state = _state.value as NoteDetailsState.Success

            viewModelScope.launch {
                if (state.noteId == null) {
                    // Todo: Validate note inputs

                    if (!state.title.isNullOrEmpty() && !state.note.isNullOrEmpty()) {
                        // Being saved for the first time
                        // Create the noteId and createdOn here

//            noteRepo.addNote(
//              Note(
//                id = UUID.randomUUID(),
//                title = state.title,
//                desc = state.desc,
//                note = state.note ?: "",
//                createdOn = Clock.System.now()
//              )
//            )
                    }
                } else {
//          noteRepo.updateNote(
//            noteId = state.noteId,
//            title = state.title,
//            desc = state.desc,
//            note = state.note ?: "",
//            editedOn = Clock.System.now()
//          )
                }
            }
        }
    }

    private fun updateSuccessState(
        state: NoteDetailsState,
        update: (NoteDetailsState.Success) -> NoteDetailsState.Success
    ): NoteDetailsState {
        return if (state is NoteDetailsState.Success) {
            update(state)
        } else {
            state
        }
    }

    fun addNote(note: AddTextNote) {
        _addState.update {
            AddNoteState.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteRepo.addNote(
                    TextNote(
                        title = note.title,
                        desc = note.desc,
                        note = note.note,
                        id = UUID.randomUUID(),
                        createdOn = Clock.System.now()
                    )
                )

                _addState.update {
                    AddNoteState.Success
                }
            } catch (e: Exception) {
                _addState.update {
                    AddNoteState.Error(e.localizedMessage ?: "Something broke")
                }
            }
        }
    }
}