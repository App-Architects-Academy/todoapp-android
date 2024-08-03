package academy.apparchitects.todoapp.data

import academy.apparchitects.todoapp.data.model.Todo

interface TodoRepository {
    suspend fun addTask(todo: Todo)
    suspend fun getAllTaks(): List<Todo>
    suspend fun getTasksWithCategory(categoryId: String): List<Todo>
    suspend fun getTaskById(taskId: String): Todo
    suspend fun searchTasks(query: String): List<Todo>
    suspend fun updateTask(taskId: String, task: Todo)
    suspend fun markTaskAsComplete(taskId: String)
}