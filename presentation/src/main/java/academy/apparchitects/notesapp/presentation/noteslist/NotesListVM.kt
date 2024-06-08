package academy.apparchitects.notesapp.presentation.noteslist

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

class NotesListVM : BaseViewModel<NotesListStates>() {
    private val _state: MutableStateFlow<NotesListStates> = MutableStateFlow(NotesListStates.Idle)

    override val state: StateFlow<NotesListStates> = _state

    fun fetchNotes() {
        _state.update {
            NotesListStates.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            _state.update {
                NotesListStates.Error("Not yet implemented")
            }
        }
    }
}