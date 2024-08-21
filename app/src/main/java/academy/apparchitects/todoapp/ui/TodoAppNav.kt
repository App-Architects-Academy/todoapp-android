package academy.apparchitects.todoapp.ui

import academy.apparchitects.todoapp.data.model.SerializableNote
import academy.apparchitects.todoapp.data.model.SerializableTodo
import academy.apparchitects.todoapp.ui.home.TodoHomeScreen
import academy.apparchitects.todoapp.ui.screens.addnote.AddNoteScreen
import academy.apparchitects.todoapp.ui.screens.notedetails.NoteDetailsScreen
import academy.apparchitects.todoapp.ui.screens.notelist.NotesListScreen
import academy.apparchitects.todoapp.ui.theme.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun TodoAppNav(
    navController: NavHostController,
    startDestination: Destinations = Destinations.TodosList
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Destinations.TodosList> {

            TodoHomeScreen(
                onFabClicked = {

                },
                onItemClicked = {

                },
                onCompletedClicked = {

                }
            )
        }

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

        composable<Destinations.TodosList> {
//      TodosListScreen(
//        onTodoClick = { todoId, todo ->
//          navController.navigate(
//            Destinations.SerializableTodoDetail(
//              todoId = todoId,
//              todo = todo.toSerializable()
//            )
//          )
//        },
//        onAddTodoClick = {
//          navController.navigate(Destinations.AddTodo)
//        }
//      )
        }

        composable<Destinations.TodoDetail> { backStackEntry ->
            val args: Destinations.TodoDetail = backStackEntry.toRoute()

//      TodoDetailsScreen(
//        navigateUp = { navController.navigateUp() },
//        onShareClick = { _ -> },
//        noteId = args.todoId
//      )
        }


        composable<Destinations.SerializableTodoDetail>(
            typeMap = mapOf(typeOf<SerializableTodo>() to parcelableType<SerializableTodo>())
        ) { backStackEntry ->
            val args: Destinations.SerializableTodoDetail = backStackEntry.toRoute()

//      TodoDetailsScreen(
//        navigateUp = { navController.navigateUp() },
//        onShareClick = { _ -> },
//        todoId = args.todoId,
//        serializableTodo = args.todo
//      )
        }

        composable<Destinations.AddTodo> {
//      AddTodoScreen(
//        navigateUp = { navController.navigateUp() },
//        onTodoAdded = { navController.navigateUp() }
//      )
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

    @Serializable
    data object TodosList : Destinations()

    @Serializable
    data class TodoDetail(
        val todoId: String? = null
    ) : Destinations()

    @Serializable
    data class SerializableTodoDetail(
        val todoId: String? = null,
        val todo: SerializableTodo
    ) : Destinations()

    @Serializable
    data object AddTodo : Destinations()

    @Serializable
    data object Completed : Destinations()
}