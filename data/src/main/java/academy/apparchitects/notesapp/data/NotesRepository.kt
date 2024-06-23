package academy.apparchitects.notesapp.data

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.NoteType
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote

interface NotesRepository {
    suspend fun addNote(note: Note)

    suspend fun getReminders(): List<Reminder>
    suspend fun getTextNotes(): List<TextNote>
    suspend fun getTasks(): List<DailyTask>

    suspend fun getAllNotes(): List<Note>
    
    suspend fun getNote(noteId: String, noteType: NoteType): Note
}