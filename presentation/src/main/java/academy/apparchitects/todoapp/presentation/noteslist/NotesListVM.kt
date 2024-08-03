package academy.apparchitects.todoapp.presentation.noteslist

import academy.apparchitects.todoapp.data.NotesRepository
import academy.apparchitects.todoapp.presentation.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListVM @Inject constructor(
    private val noteRepo: NotesRepository
) : BaseViewModel<NotesListStates>() {

    private val _state: MutableStateFlow<NotesListStates> = MutableStateFlow(NotesListStates.Idle)

    override val state: StateFlow<NotesListStates> = _state

    fun fetchNotes(isSilent: Boolean) {
        if (!isSilent) {
            _state.update {
                NotesListStates.Loading
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val textNotes = noteRepo.getTextNotes()
                val tasks = noteRepo.getTasks()
                val reminders = noteRepo.getReminders()

                if (textNotes.isEmpty() && tasks.isEmpty() && reminders.isEmpty()) {
                    _state.update {
                        NotesListStates.Empty
                    }
                } else {
                    _state.update {
                        NotesListStates.Success(
                            recentTextNotes = textNotes,
                            dailyTasks = tasks,
                            reminders = reminders
                        )
                    }
                }

            } catch (t: Throwable) {
                _state.update {
                    NotesListStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }
}