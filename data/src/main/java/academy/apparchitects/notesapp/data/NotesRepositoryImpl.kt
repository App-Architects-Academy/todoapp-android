package academy.apparchitects.notesapp.data

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.NoteType
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote
import academy.apparchitects.notesapp.data.source.NotesLocalDS

class NotesRepositoryImpl(
    val notesLocalDS: NotesLocalDS
) : NotesRepository {

    override suspend fun addNote(note: Note) {
        notesLocalDS.addNote(note)
    }

    override suspend fun getReminders(): List<Reminder> {
        return notesLocalDS.getReminders()
    }

    override suspend fun getTextNotes(): List<TextNote> {
        return notesLocalDS.getTextNotes()
    }

    override suspend fun getTasks(): List<DailyTask> {
        return notesLocalDS.getTasks()
    }

    override suspend fun getAllNotes(): List<Note> {
        return notesLocalDS.getAllNotes()
    }

    override suspend fun getNote(noteId: String, noteType: NoteType): Note {
        return notesLocalDS.getNote(noteId)
    }
}