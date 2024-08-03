package academy.apparchitects.todoapp.ui.screens.notelist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentNotesItem(
  title: String,
  desc: String,
  content: String,
  onNoteClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.5f)
      .padding(end = 8.dp)
      .clip(RoundedCornerShape(4.dp))
      .background(Color(0xFFF3EBA9))
      .padding(horizontal = 12.dp, vertical = 8.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(modifier = Modifier.clickable { onNoteClick() }) {
      Text(text = title, color = Color.Black, fontWeight = FontWeight.W600, fontSize = 18.sp)
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = desc,
        color = Color.DarkGray,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = content, color = Color.Gray, fontWeight = FontWeight.W400, fontSize = 14.sp)
    }
  }
}