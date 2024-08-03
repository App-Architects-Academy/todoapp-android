package academy.apparchitects.todoapp.presentation.noteslist

import academy.apparchitects.todoapp.data.model.DailyTask
import academy.apparchitects.todoapp.data.model.Reminder
import academy.apparchitects.todoapp.data.model.TextNote

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

    object Empty : NotesListStates()
}