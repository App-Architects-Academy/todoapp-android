package academy.apparchitects.todoapp.ui.screens.notedetails

import academy.apparchitects.todoapp.data.model.TextNote
import academy.apparchitects.todoapp.data.model.SerializableNote
import academy.apparchitects.todoapp.presentation.notedetails.NoteDetailsState
import academy.apparchitects.todoapp.presentation.notedetails.NoteDetailsViewModel
import academy.apparchitects.todoapp.ui.components.Loader
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    navigateUp: () -> Unit,
    onShareClick: (TextNote?) -> Unit,
    noteId: String? = null,
  // TODO: This is hacky for now to pass the note object to the details screen
  // Change it once repository layer is there
    serializableNote: SerializableNote? = null,
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = hiltViewModel()
) {
  val state: NoteDetailsState by viewModel.state.collectAsState()
  val noteBgColor = Color(0xFFFFFFE2)

  var isDetailsLoaded: Boolean by rememberSaveable {
    mutableStateOf(false)
  }

  LaunchedEffect(isDetailsLoaded) {
    if (!isDetailsLoaded) {
      delay(500)
      viewModel.initialUiState(noteId, serializableNote)
      isDetailsLoaded = true
    }
  }

  BackHandler(true) {
    // Saving the note on back pressed eagerly
    viewModel.addOrUpdateNoteInDb()
    navigateUp()
  }

  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {},
        actions = {
          IconButton(onClick = {
            // Shareable only when state is success and noteId is not null (i.e saved note)
            if (state is NoteDetailsState.Success) {
              val successState = state as NoteDetailsState.Success
              successState.apply {
                noteId?.let {
//                      onShareClick(
//                        Note(
//                          id = UUID.fromString(it),
//                          title = title,
//                          desc = desc,
//                          note = note ?: "",
//                          createdOn = createdOn
//                        )
//                      )
                }
              }
            }
          }) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "share",
              tint = Color(0xFF030303)
            )
          }
        },
        navigationIcon = {
          IconButton(onClick = {
            viewModel.addOrUpdateNoteInDb()
            navigateUp()
          }) {
            Icon(
              Icons.Default.ArrowBack,
              contentDescription = "back",
              tint = Color(0xFF030303)
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = noteBgColor
        ),
        windowInsets = WindowInsets(top = 0.dp, bottom = 0.dp)
      )
    },
    modifier = modifier
  ) { scaffoldPadding ->
    Box(
      modifier = modifier
        .fillMaxSize()
        .background(noteBgColor)
    ) {
      when (state) {
        is NoteDetailsState.Error -> {}

        NoteDetailsState.Loading -> {
          Loader()
        }

        is NoteDetailsState.Success -> {
          NoteDetailsContent(
            state = state as NoteDetailsState.Success,
            onTitleChange = { viewModel.updateTitle(it) },
            onDescChange = { viewModel.updateDescription(it) },
            onNoteChange = { viewModel.updateContent(it) },
            backgroundColor = noteBgColor,
            modifier = Modifier.padding(scaffoldPadding)
          )
        }
      }
    }
  }
}