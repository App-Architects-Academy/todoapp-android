package academy.apparchitects.todoapp.data.di

import academy.apparchitects.todoapp.data.NotesRepository
import academy.apparchitects.todoapp.data.NotesRepositoryImpl
import academy.apparchitects.todoapp.data.source.NotesLocalDS
import academy.apparchitects.todoapp.data.source.db.NoteTypeConverter
import academy.apparchitects.todoapp.data.source.db.NotesDAO
import academy.apparchitects.todoapp.data.source.db.NotesDB
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesRepository(notesLocalDS: NotesLocalDS): NotesRepository =
        NotesRepositoryImpl(notesLocalDS)

    @Provides
    @Singleton
    fun providesNotesDB(@ApplicationContext applicationContext: Context): NotesDB =
        Room.databaseBuilder(
            applicationContext,
            NotesDB::class.java, "notes-db",
        ).addTypeConverter(NoteTypeConverter()).build()

    @Provides
    @Singleton
    fun providesNotesDao(notesDB: NotesDB): NotesDAO = notesDB.notesDao()
}