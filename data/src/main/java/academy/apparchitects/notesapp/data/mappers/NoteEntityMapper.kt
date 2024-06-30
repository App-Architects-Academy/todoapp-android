package academy.apparchitects.notesapp.data.mappers

import academy.apparchitects.notesapp.data.model.DailyTask
import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.NoteType
import academy.apparchitects.notesapp.data.model.Reminder
import academy.apparchitects.notesapp.data.model.TextNote
import academy.apparchitects.notesapp.data.model.TodoItem
import academy.apparchitects.notesapp.data.source.db.NoteEntity
import academy.apparchitects.notesapp.data.source.db.TodoItemsEntity
import academy.apparchitects.notesapp.data.source.db.TodoNote
import kotlinx.datetime.Instant
import java.util.UUID
import javax.inject.Inject

class NoteEntityMapper @Inject constructor() {
    fun objToEntity(note: Note): NoteEntity {
        return when (note) {
            is TextNote -> NoteEntity(
                id = note.id.toString(),
                note = note.note,
                desc = note.desc,
                title = note.title,
                type = NoteType.Text,
                createdOn = note.createdOn.toEpochMilliseconds(),
                updatedOn = note.createdOn.toEpochMilliseconds()
            )

            is DailyTask -> NoteEntity(
                id = note.id.toString(),
                desc = note.todoItems.joinToString(",\n"),
                title = note.title,
                type = NoteType.Text,
                createdOn = note.createdOn.toEpochMilliseconds(),
                updatedOn = note.createdOn.toEpochMilliseconds()
            )

            is Reminder -> NoteEntity(
                id = note.id.toString(),
                title = note.reminder,
                type = NoteType.Text,
                isCompleted = note.isCompleted,
                createdOn = note.createdOn.toEpochMilliseconds(),
                updatedOn = note.createdOn.toEpochMilliseconds()
            )
        }
    }

    fun objToEntity(note: DailyTask): TodoNote {
        return TodoNote(
            noteEntity = NoteEntity(
                id = note.id.toString(),
                desc = note.todoItems.joinToString(",\n"),
                title = note.title,
                type = NoteType.Text,
                createdOn = note.createdOn.toEpochMilliseconds(),
                updatedOn = note.createdOn.toEpochMilliseconds()
            ),
            todos = note.todoItems.map {
                TodoItemsEntity(
                    id = UUID.randomUUID().toString(),
                    noteId = note.id.toString(),
                    isCompleted = it.isCompleted,
                    desc = it.item
                )
            }
        )
    }

    fun entityToObj(note: NoteEntity): Note {
        return when (note.type) {
            NoteType.Text -> TextNote(
                id = UUID.fromString(note.id),
                title = note.title,
                createdOn = Instant.fromEpochMilliseconds(note.createdOn),
                editedOn = Instant.fromEpochMilliseconds(note.createdOn),
                note = note.note ?: "",
                desc = note.desc,
            )

            NoteType.Task -> DailyTask(
                id = UUID.fromString(note.id),
                title = note.title,
                todoItems = emptyList(),
                createdOn = Instant.fromEpochMilliseconds(note.createdOn),
                editedOn = Instant.fromEpochMilliseconds(note.createdOn),
            )

            NoteType.Reminder -> Reminder(
                id = UUID.fromString(note.id),
                reminder = note.title ?: "",
                createdOn = Instant.fromEpochMilliseconds(note.createdOn),
                editedOn = Instant.fromEpochMilliseconds(note.createdOn),
            )
        }
    }

    fun entityToObj(todoNote: TodoNote): DailyTask {
        return DailyTask(
            id = UUID.fromString(todoNote.noteEntity.id),
            title = todoNote.noteEntity.title,
            todoItems = todoNote.todos.map {
                TodoItem(
                    item = it.desc,
                    isCompleted = it.isCompleted
                )
            },
            createdOn = Instant.fromEpochMilliseconds(todoNote.noteEntity.createdOn),
            editedOn = Instant.fromEpochMilliseconds(todoNote.noteEntity.createdOn),
        )
    }
}