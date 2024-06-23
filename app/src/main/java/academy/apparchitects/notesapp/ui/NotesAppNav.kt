package academy.apparchitects.notesapp.ui

import academy.apparchitects.notesapp.data.model.SerializableNote
import academy.apparchitects.notesapp.ui.screens.notedetails.NoteDetailsScreen
import academy.apparchitects.notesapp.ui.screens.notelist.NotesListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun NotesAppNav(
  navController: NavHostController,
  startDestination: Destinations = Destinations.NotesList
) {

  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {

    composable<Destinations.NotesList> {
      NotesListScreen(
        onNoteClick = { noteId, note ->
//        navController.navigate(
//          Destinations.NoteDetail(noteId)
//        )
        // TODO: This is hacky for now to pass the note object to the details screen
        // Change it once repository layer is there
        navController.navigate(
          Destinations.SerializableNoteDetail(
            noteId = noteId,
            note = note.toSerializable()
          )
        )
      },
        onAddNoteClick = {
          navController.navigate(Destinations.NoteDetail())
        }
      )
    }

    composable<Destinations.NoteDetail> { backStackEntry ->
      val args: Destinations.NoteDetail = backStackEntry.toRoute()

      NoteDetailsScreen(
        navigateUp = { navController.navigateUp() },
        onShareClick = { _ -> },
        noteId = args.noteId
      )
    }

    composable<Destinations.SerializableNoteDetail>(
      typeMap = mapOf(typeOf<SerializableNote>() to parcelableType<SerializableNote>())
    ) { backStackEntry ->
      val args: Destinations.SerializableNoteDetail = backStackEntry.toRoute()

      NoteDetailsScreen(
        navigateUp = { navController.navigateUp() },
        onShareClick = { _ -> },
        noteId = args.noteId,
        serializableNote = args.note
      )
    }

  }
}

sealed class Destinations {

  @Serializable
  data object NotesList : Destinations()

  @Serializable
  data class NoteDetail(
    val noteId: String? = null
  ) : Destinations()

  @Serializable
  data class SerializableNoteDetail(
    val noteId: String? = null,
    val note: SerializableNote
  ) : Destinations()
}