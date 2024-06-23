package academy.apparchitects.notesapp.ui.screens.note_list.components

import academy.apparchitects.notesapp.data.DailyTask
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp

@Composable
fun DailyTaskItem(
  dailyTask: DailyTask,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(4.dp))
      .background(Color(0xFFF3EBA9))
      .padding(horizontal = 12.dp, vertical = 8.dp),
    contentAlignment = Alignment.Center
  ) {

    Column {
      Text(
        text = dailyTask.title ?: "",
        color = Color.Black, fontWeight = FontWeight.W600, fontSize = 18.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      dailyTask.todoItems.forEach { item ->
        TodoListItem(
          todoItem = item,
          onCheckedChange = { a, b ->

          }
        )
      }
    }
  }
}