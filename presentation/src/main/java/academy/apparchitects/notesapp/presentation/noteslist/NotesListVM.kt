package academy.apparchitects.notesapp.presentation.noteslist

import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.presentation.base.BaseViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import kotlin.random.Random

class NotesListVM : BaseViewModel<NotesListStates>() {
    private val _state: MutableStateFlow<NotesListStates> = MutableStateFlow(NotesListStates.Idle)

    override val state: StateFlow<NotesListStates> = _state

    fun fetchNotes() {
        _state.update {
            NotesListStates.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            try {
                val notesList = (0..10).toList().map {
                    Note(
                        id = UUID.randomUUID(),
                        title = "Note $it",
                        note = "Some note $it",
                        createdOn = Clock.System.now()
                    )
                }
                val favNotes = (0..3).toList().map {
                    Note(
                        id = UUID.randomUUID(),
                        title = "Fav Note $it",
                        note = "Some note $it",
                        createdOn = Clock.System.now()
                    )
                }
                _state.update {
                    NotesListStates.Success(
                        favNotes, notesList
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