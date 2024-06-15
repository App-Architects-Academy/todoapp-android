package academy.apparchitects.notesapp.ui.screens

import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.presentation.noteslist.NotesListStates
import academy.apparchitects.notesapp.presentation.noteslist.NotesListVM
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotesListScreen(
    onNoteClick: (String) -> Unit,
    notesListVM: NotesListVM = viewModel(modelClass = NotesListVM::class)
) {
    val state = notesListVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        notesListVM.fetchNotes()
    }

    val currentState = state.value
    when(currentState) {
        is NotesListStates.Success -> {
            NotesListSuccess(currentState.favNotes, currentState.otherNotes, onNoteClick = onNoteClick)
        }
        is NotesListStates.Error -> {
            Text(text = currentState.errorDetails)
        }
        is NotesListStates.Idle -> {
            Text(text = "Nothing happenning")
        }
        is NotesListStates.Loading -> {
            Text(text = "Loading")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListSuccess(
    favNotes: List<Note>,
    otherNotes: List<Note>,
    onNoteClick: (String) -> Unit
) {
    LazyColumn {
        item {
            Text("Fav Notes", textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            Divider()
        }

        items(favNotes) { note ->
            Card(onClick = { onNoteClick(note.id.toString()) }) {
                Column {
                    Text(text = note.id.toString())
                    Text(text = note.title ?: "No Title")
                    Text(text = note.note)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }

        item {
            Text("Other Notes", textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            Divider()
        }

        items(otherNotes) { note ->
            Card(onClick = { onNoteClick(note.id.toString()) }) {
                Column {
                    Text(text = note.id.toString())
                    Text(text = note.title ?: "No Title")
                    Text(text = note.note)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}
