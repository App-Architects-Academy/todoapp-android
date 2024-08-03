package academy.apparchitects.todoapp.data.source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface NotesDAO {
    @Query("select * from notes")
    fun getAllNotes(): List<NoteEntity>

    @Query("select * from notes WHERE type = 2")
    fun getReminders(): List<NoteEntity>

    @Query("select * from notes WHERE type = 0")
    fun getTextNotes(): List<NoteEntity>

    @Transaction
    @Query("select * from notes WHERE type = 1")
    fun getTasks(): List<TodoNote>

    @Query("select * from todos WHERE  note_id = :noteId")
    fun getTodos(noteId: String): List<TodoItemsEntity>

    @Insert
    fun addNote(noteEntity: NoteEntity)

    @Insert
    fun addTodos(todoItemsEntities: List<TodoItemsEntity>)

    @Update
    fun editNote(noteEntity: NoteEntity)

    @Query("select * from notes WHERE id = :noteId")
    fun getNote(noteId: String): NoteEntity

    @Delete
    fun delete(noteEntity: NoteEntity)
}