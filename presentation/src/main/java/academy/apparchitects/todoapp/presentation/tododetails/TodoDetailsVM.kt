package academy.apparchitects.todoapp.presentation.tododetails

import academy.apparchitects.todoapp.data.TodoRepository
import academy.apparchitects.todoapp.presentation.base.BaseViewModel
import academy.apparchitects.todoapp.presentation.todoaddedit.AddEditTaskStates
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailsVM @Inject constructor(
    private val repo: TodoRepository
) : BaseViewModel<TaskDetailsStates>() {

    private val _state: MutableStateFlow<TaskDetailsStates> = MutableStateFlow(TaskDetailsStates.Idle)

    override val state: StateFlow<TaskDetailsStates> = _state

    fun fetchTask(taskId: String, isSilent: Boolean) {
        if (!isSilent) {
            _state.update {
                TaskDetailsStates.Loading(taskId)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = repo.getTaskById(taskId)

                _state.update {
                    TaskDetailsStates.Success(
                        task
                    )
                }

            } catch (t: Throwable) {
                _state.update {
                    TaskDetailsStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }

    fun markTaskAsComplete(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update {
                    TaskDetailsStates.Loading(
                        taskId
                    )
                }
                repo.markTaskAsComplete(taskId)
                val task = repo.getTaskById(taskId)

                _state.update {
                    TaskDetailsStates.Success(
                        task
                    )
                }

            } catch (t: Throwable) {
                _state.update {
                    TaskDetailsStates.Error(t.message ?: "Not yet implemented")
                }
            }
        }
    }
}