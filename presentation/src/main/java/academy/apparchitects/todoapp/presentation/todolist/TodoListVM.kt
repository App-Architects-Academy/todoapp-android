package academy.apparchitects.todoapp.presentation.todolist

import academy.apparchitects.todoapp.data.NotesRepository
import academy.apparchitects.todoapp.data.TodoRepository
import academy.apparchitects.todoapp.presentation.base.BaseViewModel
import academy.apparchitects.todoapp.presentation.noteslist.NotesListStates
import academy.apparchitects.todoapp.presentation.tododetails.TaskDetailsStates
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListVM @Inject constructor(
    private val repo: TodoRepository
) : BaseViewModel<TaskListStates>() {

    private val _state: MutableStateFlow<TaskListStates> = MutableStateFlow(TaskListStates.Idle)

    override val state: StateFlow<TaskListStates> = _state

    fun fetchTasks(isSilent: Boolean) {
        if (!isSilent) {
            _state.update {
                TaskListStates.Loading
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tasks = repo.getAllTaks()

                if (tasks.isEmpty()) {
                    _state.update {
                        TaskListStates.Empty
                    }
                } else {
                    _state.update {
                        TaskListStates.Success(
                            tasks = tasks
                        )
                    }
                }

            } catch (t: Throwable) {
                _state.update {
                    TaskListStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }

    fun markTaskAsComplete(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_state.value is TaskListStates.Success) {
                    _state.update {
                        (it as TaskListStates.Success).copy(isRefreshing = true)
                    }
                }
                repo.markTaskAsComplete(taskId)
                val tasks = repo.getAllTaks()//Not a good practice

                _state.update {
                    TaskListStates.Success(
                        tasks
                    )
                }

            } catch (t: Throwable) {
                _state.update {
                    TaskListStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }
}