package academy.apparchitects.todoapp.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NoteEntity::class, TodoItemsEntity::class], version = 1)
@TypeConverters(NoteTypeConverter::class)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDao(): NotesDAO
}