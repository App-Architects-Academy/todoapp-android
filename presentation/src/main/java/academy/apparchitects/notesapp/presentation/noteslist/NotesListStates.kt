package academy.apparchitects.notesapp.presentation.noteslist

import academy.apparchitects.notesapp.data.DailyTask
import academy.apparchitects.notesapp.data.Note

sealed class NotesListStates {

    data class Success(
        val recentNotes: List<Note>,
        val dailyTasks: List<DailyTask>,
        val reminders: List<Note>
    ) : NotesListStates()

    data class Error(
        val errorDetails: String
    ) : NotesListStates()

    object Loading : NotesListStates()

    object Idle : NotesListStates()
}