package academy.apparchitects.notesapp

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
fun NotesAppNav(navController: NavHostController, startDestination: Destinations = Destinations.NotesList) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Destinations.NotesList>() {
            Column {
                Text(text = "Home")
                Button(onClick = {
                    val id = Random(100).nextInt()
                    navController.navigate(Destinations.NoteDetail("note_$id"))
                }) {
                    Text(text = "Click to go to Note Details")
                }
            }
        }

        composable<Destinations.NoteDetail>() { backStackEntry ->
            val noteDetail: Destinations.NoteDetail = backStackEntry.toRoute()
            Column {
                Text(text = "Note Details ${noteDetail.noteId}")
                Button(onClick = { navController.navigateUp() }) {
                    Text(text = "Click to go back")
                }
            }
        }
    }
}

sealed class Destinations {

    @Serializable
    object NotesList: Destinations()

    @Serializable
    data class NoteDetail(val noteId: String): Destinations()
}