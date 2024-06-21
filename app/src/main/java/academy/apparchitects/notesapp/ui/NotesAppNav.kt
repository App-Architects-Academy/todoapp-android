package academy.apparchitects.notesapp.ui

import academy.apparchitects.notesapp.ui.screens.NotesListScreen
import academy.apparchitects.notesapp.ui.screens.note_details.NoteDetailsScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.random.Random


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
            NotesListScreen(onNoteClick = {
                navController.navigate(Destinations.NoteDetail(it))
            },
              onAddNoteClick = {
                navController.navigate(Destinations.NoteDetail())
              }
            )
        }

        composable<Destinations.NoteDetail> { backStackEntry ->
            val arg: Destinations.NoteDetail = backStackEntry.toRoute()

            NoteDetailsScreen(
              navigateUp = {navController.navigateUp()},
              onShareClick = { _ ->

              },
              noteId = arg.noteId
            )
        }

    }
}

sealed class Destinations {

    @Serializable
    object NotesList: Destinations()

    @Serializable
    data class NoteDetail(val noteId: String? = null): Destinations()
}