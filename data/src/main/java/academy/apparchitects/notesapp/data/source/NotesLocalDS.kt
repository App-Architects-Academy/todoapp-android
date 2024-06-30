package academy.apparchitects.notesapp.data.source

import academy.apparchitects.notesapp.data.mappers.NoteEntityMapper
import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.NoteType
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote
import academy.apparchitects.notesapp.data.source.db.NotesDAO
import academy.apparchitects.notesapp.data.source.db.TodoItemsEntity
import academy.apparchitects.notesapp.data.source.db.TodoNote
import java.util.UUID
import javax.inject.Inject

class NotesLocalDS @Inject constructor(
    private val notesDAO: NotesDAO,
    private val notesMapper: NoteEntityMapper,
) {
    private val textNotes: MutableList<TextNote> = mutableListOf()
    private val tasks: MutableList<DailyTask> = mutableListOf()
    private val reminders: MutableList<Reminder> = mutableListOf()

    fun addNote(note: Note) {
        notesDAO.addNote(notesMapper.objToEntity(note))
        if (note is DailyTask)  {
            notesDAO.addTodos(
                note.todoItems.map {
                    TodoItemsEntity(
                        id = UUID.randomUUID().toString(),
                        desc = it.item,
                        isCompleted = it.isCompleted,
                        noteId = note.id.toString()
                    )
                }
            )
        }
    }

    fun getReminders(): List<Reminder> {
        return notesDAO.getReminders().map {
            notesMapper.entityToObj(it) as Reminder
        }
    }

    fun getTextNotes(): List<TextNote> {
        return notesDAO.getTextNotes().map {
            notesMapper.entityToObj(it) as TextNote
        }
    }

    fun getTasks(): List<DailyTask> {
        return notesDAO.getTasks().map {
            notesMapper.entityToObj(it)
        }
    }

    fun getAllNotes(): List<Note> {
        return notesDAO.getAllNotes().map(notesMapper::entityToObj)
    }

    fun getNote(noteId: String): Note {
        val noteEntity = notesDAO.getNote(noteId)

        val note = if (noteEntity.type == NoteType.Task) {
            val todos = notesDAO.getTodos(noteId)
            val todoNote = TodoNote(
                noteEntity = noteEntity,
                todos = todos
            )

            notesMapper.entityToObj(todoNote)
        } else {
            notesMapper.entityToObj(noteEntity)
        }

        return note
    }
}