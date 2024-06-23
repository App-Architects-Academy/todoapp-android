package academy.apparchitects.notesapp.ui.screens.notelist.components

import academy.apparchitects.notesapp.data.model.TodoItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoListItem(
    todoItem: TodoItem,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean, TodoItem) -> Unit
) {

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Checkbox(
      checked = todoItem.isCompleted,
      onCheckedChange = {
        onCheckedChange.invoke(
          todoItem.isCompleted.not(),
          todoItem
        )
      }
    )

    /* Spacer(
         modifier = Modifier.width(4.dp)
     )*/

    Text(
      text = todoItem.item,
      color = Color.Gray,
      fontWeight = FontWeight.W400,
      fontSize = 14.sp
    )

  }
}