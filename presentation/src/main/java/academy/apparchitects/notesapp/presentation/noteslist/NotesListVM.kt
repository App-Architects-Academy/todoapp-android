package academy.apparchitects.notesapp.presentation.noteslist

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote
import academy.apparchitects.notesapp.data.model.TodoItem
import academy.apparchitects.notesapp.presentation.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NotesListVM @Inject constructor(): BaseViewModel<NotesListStates>() {

    private val _state: MutableStateFlow<NotesListStates> = MutableStateFlow(NotesListStates.Idle)

    override val state: StateFlow<NotesListStates> = _state

    fun fetchNotes() {
        _state.update {
            NotesListStates.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            try {
                val dailyTaskList = (0..10).toList().map {

                   val itemNames = listOf("Pani Poori", "Samosa", "Carrot", "Eggs","Mushroom","Pasta","Chawal")

                    val itemsList = (0..(1..6).random()).toList().map {
                        TodoItem(
                            isCompleted = false,
                            item = itemNames.random()
                        )
                    }

                    DailyTask(
                        id = UUID.randomUUID(),
                        title = "Note ${it+1}",
                        todoItems = itemsList,
                        createdOn = Clock.System.now()
                    )
                }
                val recentTextNotes = (0..5).toList().map {
                    TextNote(
                        id = UUID.randomUUID(),
                        title = "Goal Gradient Effect",
                        desc = "Laws of UX",
                        note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        createdOn = Clock.System.now()
                    )
                }
                val reminders = (0..8).toList().map {
                    Reminder(
                        id = UUID.randomUUID(),
                        reminder = "Reminder $it",
                        isCompleted = it == 6
                    )
                }
                _state.update {
                    NotesListStates.Success(
                        recentTextNotes = recentTextNotes,
                        dailyTasks = dailyTaskList,
                        reminders = reminders
                    )
                }
            } catch (t: Throwable) {
                _state.update {
                    NotesListStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }
}