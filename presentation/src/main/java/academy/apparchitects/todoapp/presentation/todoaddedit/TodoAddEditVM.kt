package academy.apparchitects.todoapp.presentation.todoaddedit

import academy.apparchitects.todoapp.data.TodoRepository
import academy.apparchitects.todoapp.presentation.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class TodoAddEditVM @Inject constructor(
    private val repo: TodoRepository
) : BaseViewModel<AddEditTaskStates>() {

    private val _state: MutableStateFlow<AddEditTaskStates> = MutableStateFlow(AddEditTaskStates.AddTask())

    override val state: StateFlow<AddEditTaskStates> = _state

    fun addTask(
        title: String,
        dueDate: Instant,
        priority: String,
        categoryId: String,
        description: String,
    ) {

    }

    fun editTask(
        taskId: String,
        title: String,
        dueDate: Instant,
        priority: String,
        categoryId: String,
        description: String,
    ) {

    }
}