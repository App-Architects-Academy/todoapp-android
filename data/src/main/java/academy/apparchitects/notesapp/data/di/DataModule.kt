package academy.apparchitects.notesapp.data.di

import academy.apparchitects.notesapp.data.NotesRepository
import academy.apparchitects.notesapp.data.NotesRepositoryImpl
import academy.apparchitects.notesapp.data.source.NotesLocalDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesRepository(notesLocalDS: NotesLocalDS): NotesRepository =
        NotesRepositoryImpl(notesLocalDS)
}