package academy.apparchitects.notesapp.data.source.db

import academy.apparchitects.notesapp.data.model.NoteType
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val type: NoteType,
    val title: String? = null,
    val desc: String? = null,
    val note: String? = null,
    val isCompleted: Boolean = false,
    val createdOn: Long,
    val updatedOn: Long? = null
)

@Entity("todos", foreignKeys = [
    ForeignKey(entity = NoteEntity::class, parentColumns = ["id"], childColumns = ["note_id"], onDelete = ForeignKey.CASCADE)
])
data class TodoItemsEntity(
    @PrimaryKey val id: String,
    val desc: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "note_id", index = true) val noteId: String,
)

data class TodoNote(
    @Embedded val noteEntity: NoteEntity,
    @Relation(
        parentColumn = "id", entityColumn = "note_id"
    )
    val todos: List<TodoItemsEntity>
)