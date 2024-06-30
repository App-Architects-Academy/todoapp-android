package academy.apparchitects.notesapp.data.di

import academy.apparchitects.notesapp.data.NotesRepository
import academy.apparchitects.notesapp.data.NotesRepositoryImpl
import academy.apparchitects.notesapp.data.source.NotesLocalDS
import academy.apparchitects.notesapp.data.source.db.NoteTypeConverter
import academy.apparchitects.notesapp.data.source.db.NotesDAO
import academy.apparchitects.notesapp.data.source.db.NotesDB
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