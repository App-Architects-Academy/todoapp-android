package academy.apparchitects.todoapp.data

import academy.apparchitects.todoapp.data.model.DailyTask
import academy.apparchitects.todoapp.data.model.Note
import academy.apparchitects.todoapp.data.model.NoteType
import academy.apparchitects.todoapp.data.model.Reminder
import academy.apparchitects.todoapp.data.model.TextNote

interface NotesRepository {
    suspend fun addNote(note: Note)

    suspend fun getReminders(): List<Reminder>
    suspend fun getTextNotes(): List<TextNote>
    suspend fun getTasks(): List<DailyTask>

    suspend fun getAllNotes(): List<Note>
    
    suspend fun getNote(noteId: String, noteType: NoteType): Note
}