package academy.apparchitects.notesapp.presentation.noteslist

import academy.apparchitects.notesapp.data.Note

sealed class NotesListStates {
    data class Success(
        val favNotes: List<Note>,
        val otherNotes: List<Note>,
    ) : NotesListStates()

    data class Error(
        val errorDetails: String
    ) : NotesListStates()

    object Loading : NotesListStates()

    object Idle : NotesListStates()
}