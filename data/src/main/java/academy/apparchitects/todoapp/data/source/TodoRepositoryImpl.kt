package academy.apparchitects.todoapp.data.source

import academy.apparchitects.todoapp.data.TodoRepository
import academy.apparchitects.todoapp.data.model.DailyTask
import academy.apparchitects.todoapp.data.model.Note
import academy.apparchitects.todoapp.data.model.NoteType
import academy.apparchitects.todoapp.data.model.Reminder
import academy.apparchitects.todoapp.data.model.TextNote
import academy.apparchitects.todoapp.data.model.Todo
import academy.apparchitects.todoapp.data.source.NotesLocalDS
import kotlinx.datetime.Clock

class TodoRepositoryImpl(
) : TodoRepository {

  override suspend fun addTask(todo: Todo) {}

  override suspend fun getAllTaks(): List<Todo> {
    return emptyList()
  }

  override suspend fun getTasksWithCategory(categoryId: String): List<Todo> {
    return emptyList()
  }

  override suspend fun getTaskById(taskId: String): Todo {
    return Todo(
      id = "",
      title = "",
      dueDate = Clock.System.now(),
      priority = "",
      categoryId = ""
      )
  }

  override suspend fun searchTasks(query: String): List<Todo> {
    return emptyList()
  }

  override suspend fun updateTask(taskId: String, task: Todo) {

  }

  override suspend fun markTaskAsComplete(taskId: String) {

  }
}