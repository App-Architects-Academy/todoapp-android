package academy.apparchitects.notesapp.data.source

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.NoteType
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote
import javax.inject.Inject

class NotesLocalDS @Inject constructor() {
    private val textNotes: MutableList<TextNote> = mutableListOf()
    private val tasks: MutableList<DailyTask> = mutableListOf()
    private val reminders: MutableList<Reminder> = mutableListOf()

    fun addNote(note: Note) {
        when (note) {
            is TextNote -> {
                textNotes.add(note)
            }
            is DailyTask -> {
                tasks.add(note)
            }
            is Reminder -> {
                reminders.add(note)
            }
        }
    }

    fun getReminders(): List<Reminder> {
        return reminders
    }
    fun getTextNotes(): List<TextNote> {
        return textNotes
    }
    fun getTasks(): List<DailyTask> {
        return tasks
    }

    fun getAllNotes(): List<Note> {
        return reminders + textNotes + reminders
    }
    
    fun getNote(noteId: String, noteType: NoteType): Note {
        val note = when (noteType) {
            NoteType.Text -> {
                 textNotes.find {
                     it.id.toString().equals(noteId, ignoreCase = true)
                 }
            }
            NoteType.Task -> {
                tasks.find {
                    it.id.toString().equals(noteId, ignoreCase = true)
                }
            }
            NoteType.Reminder -> {
                reminders.find {
                    it.id.toString().equals(noteId, ignoreCase = true)
                }
            }
        }

        if (note == null) {
            throw RuntimeException("Note with this ID doesn't exist")
        }

        return note
    }
}