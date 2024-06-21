package academy.apparchitects.notesapp.ui.screens.note_details

import academy.apparchitects.notesapp.presentation.note_details.NoteDetailsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteDetailsContent(
  state: NoteDetailsState.Success,
  onTitleChange: (String) -> Unit,
  onDescChange: (String) -> Unit,
  onNoteChange: (String) -> Unit,
  backgroundColor: Color,
  modifier: Modifier = Modifier,
) {

  Column(
    modifier = modifier
      .background(color = backgroundColor),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    OutlinedTextField(
      value = state.title ?: "",
      onValueChange = onTitleChange,
      modifier = Modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .padding(horizontal = 16.dp),
      textStyle = TextStyle.Default.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.W600,
        color = Color(0xFF6B1B1C)
      ),
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
      )
    )

    OutlinedTextField(
      value = state.desc ?: "",
      onValueChange = onDescChange,
      modifier = Modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .padding(horizontal = 16.dp),
      textStyle = TextStyle.Default.copy(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        color = Color.Black
      ),
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
      value = state.note ?: "",
      onValueChange = onNoteChange,
      modifier = Modifier
        .fillMaxSize(1f)
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
  }
}

