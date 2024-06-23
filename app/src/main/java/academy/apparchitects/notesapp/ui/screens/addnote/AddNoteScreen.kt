package academy.apparchitects.notesapp.ui.screens.addnote

import academy.apparchitects.notesapp.presentation.notedetails.AddNoteState
import academy.apparchitects.notesapp.presentation.notedetails.AddTextNote
import academy.apparchitects.notesapp.presentation.notedetails.NoteDetailsViewModel
import academy.apparchitects.notesapp.ui.components.Loader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    onNoteAdded: () -> Unit
) {

    val state = viewModel.addState.collectAsStateWithLifecycle()
    val noteBgColor = Color(0xFFFFFFE2)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Add New Note")
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
            modifier = Modifier.padding(scaffoldPadding)
        ) {
            when (state.value) {
                is AddNoteState.Init -> {
                    AddNoteContent(
                        noteBgColor, modifier, state.value as AddNoteState.Init
                    ) {
                        viewModel.addNote(it)
                    }
                }

                is AddNoteState.Error -> {

                }

                AddNoteState.Loading -> {
                    Loader()
                }

                AddNoteState.Success -> {
                    onNoteAdded()
                }
            }
        }
    }
}

@Composable
fun AddNoteContent(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    state: AddNoteState.Init,
    onSaveClick: (AddTextNote) -> Unit
) {
    var noteTitle: String by remember {
        mutableStateOf("")
    }
    var noteDesc: String by remember {
        mutableStateOf("")
    }
    var note: String by remember {
        mutableStateOf("")
    }

    var noteTitleError: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = noteTitle,
            onValueChange = {
                noteTitle = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            textStyle = TextStyle.Default.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color(0xFF6B1B1C)
            ),
            maxLines = 2,
            placeholder = {
                Text(
                    text = "Title",
                    style = TextStyle.Default.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.LightGray
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            isError = noteTitleError,
        )

        OutlinedTextField(
            value = noteDesc,
            onValueChange = {
                noteDesc = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = Color.Black
            ),
            maxLines = 2,
            placeholder = {
                Text(
                    text = "Description",
                    style = TextStyle.Default.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.LightGray
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )

        OutlinedTextField(
            value = note,
            onValueChange = {
                note = it
            },
            modifier = Modifier
                .fillMaxSize(0.9f)
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ),
            placeholder = {
                Text(
                    text = "Enter note here",
                    style = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.LightGray
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )

        Button(onClick = {
            //validation
            if (noteTitle.isBlank()) {
                noteTitleError = true
            } else {
                onSaveClick(
                    AddTextNote(
                        title = noteTitle,
                        note = note,
                        desc = noteDesc,
                    )
                )
            }
        }) {
            Text(text = "Save Note")
        }
    }
}