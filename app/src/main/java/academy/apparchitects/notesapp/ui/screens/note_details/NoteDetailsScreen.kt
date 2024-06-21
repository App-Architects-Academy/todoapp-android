package academy.apparchitects.notesapp.ui.screens.note_details

import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.presentation.note_details.NoteDetailsState
import academy.apparchitects.notesapp.presentation.note_details.NoteDetailsViewModel
import academy.apparchitects.notesapp.ui.components.Loader
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
  navigateUp: () -> Unit,
  onShareClick: (Note?) -> Unit,
  noteId: String? = null,
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
      viewModel.initialUiState(noteId)
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
      TopAppBar(
        title = {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp),
            horizontalArrangement = Arrangement.End
          ) {
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
                contentDescription = "share"
              )
            }
          }
        },
        navigationIcon = {
          IconButton(onClick = {
            viewModel.addOrUpdateNoteInDb()
            navigateUp()
          }) {
            Icon(
              Icons.Default.ArrowBack,
              contentDescription = "back"
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = noteBgColor
        )
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