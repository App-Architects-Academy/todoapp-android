package academy.apparchitects.notesapp.ui.screens.note_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ReminderItem(
  title: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .padding(end = 8.dp)
      .clip(RoundedCornerShape(4.dp))
      .background(Color(0xFF9B9A64))
      .padding(horizontal = 12.dp, vertical = 8.dp)
      .clickable { onClick() },
    contentAlignment = Alignment.Center
  ) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.W500)
  }
}