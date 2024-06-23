package academy.apparchitects.notesapp.ui.screens.notelist

import academy.apparchitects.notesapp.data.model.Note
import academy.apparchitects.notesapp.data.model.SerializableNote
import academy.apparchitects.notesapp.data.model.TextNote
import academy.apparchitects.notesapp.presentation.noteslist.NotesListStates
import academy.apparchitects.notesapp.presentation.noteslist.NotesListVM
import academy.apparchitects.notesapp.ui.components.Loader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    onNoteClick: (String, Note) -> Unit,
    onAddNoteClick: () -> Unit,
    notesListVM: NotesListVM = hiltViewModel(),
    modifier: Modifier = Modifier
) {
  val state = notesListVM.state.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = Unit) {
    notesListVM.fetchNotes()
  }

  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {},
        actions = {
          IconButton(onClick = {}) {
            Icon(
              Icons.Default.Search,
              contentDescription = "search"
            )
          }
        },
        navigationIcon = {
          IconButton(onClick = {}) {
            Icon(
              Icons.Default.Menu,
              contentDescription = "menu"
            )
          }
        },
        windowInsets = WindowInsets(top = 0.dp, bottom = 0.dp),
      )
    },
    floatingActionButton = {

      FloatingActionButton(
        onClick = onAddNoteClick
      ) {
        Icon(
          modifier = Modifier
            .size(56.dp)
            .background(Color.LightGray),
          imageVector = Icons.Default.Add,
          contentDescription = "add note"
        )
      }
    },
    floatingActionButtonPosition = FabPosition.Center
  ) { scaffoldPadding ->
    Box(modifier = modifier.fillMaxSize()) {
      when (val currentState = state.value) {
        is NotesListStates.Success -> {
          NotesListSuccess(
            recentTextNotes = currentState.recentTextNotes,
            dailyTasks = currentState.dailyTasks,
            reminders = currentState.reminders,
            onNoteClick = onNoteClick,
            modifier = Modifier.padding(scaffoldPadding)
          )
        }

        is NotesListStates.Error -> {
          Text(text = currentState.errorDetails)
        }

        is NotesListStates.Idle -> {
          Text(text = "Nothing happening")
        }

        is NotesListStates.Loading -> {
          Loader()
        }
      }
    }
  }
}