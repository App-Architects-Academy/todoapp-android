package academy.apparchitects.notesapp.presentation.noteslist

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote

sealed class NotesListStates {

    data class Success(
        val recentTextNotes: List<TextNote>,
        val dailyTasks: List<DailyTask>,
        val reminders: List<Reminder>
    ) : NotesListStates()

    data class Error(
        val errorDetails: String
    ) : NotesListStates()

    object Loading : NotesListStates()

    object Idle : NotesListStates()
}