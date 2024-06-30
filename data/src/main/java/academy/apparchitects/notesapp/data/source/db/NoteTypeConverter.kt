package academy.apparchitects.notesapp.data.source.db

import academy.apparchitects.notesapp.data.model.NoteType
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class NoteTypeConverter {
    @TypeConverter
    fun noteTypeToInt(noteType: NoteType): Int = noteType.ordinal
    @TypeConverter
    fun intToNoteType(type: Int): NoteType = NoteType.entries[type]
}