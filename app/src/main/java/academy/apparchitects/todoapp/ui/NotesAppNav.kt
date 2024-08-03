package academy.apparchitects.todoapp.ui

import academy.apparchitects.todoapp.data.model.SerializableNote
import academy.apparchitects.todoapp.ui.screens.addnote.AddNoteScreen
import academy.apparchitects.todoapp.ui.screens.notedetails.NoteDetailsScreen
import academy.apparchitects.todoapp.ui.screens.notelist.NotesListScreen
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
          navController.navigate(Destinations.AddNote)
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

    composable<Destinations.AddNote> {
      AddNoteScreen(
        navigateUp = { navController.navigateUp() },
        onNoteAdded = { navController.navigateUp() }
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

  @Serializable
  data object AddNote : Destinations()
}