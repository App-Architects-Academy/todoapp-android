package academy.apparchitects.todoapp.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State> : ViewModel() {
    abstract val state: StateFlow<State>
}