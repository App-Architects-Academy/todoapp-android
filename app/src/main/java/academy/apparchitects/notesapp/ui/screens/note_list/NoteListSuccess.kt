package academy.apparchitects.notesapp.ui.screens.note_list

import academy.apparchitects.notesapp.data.DailyTask
import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.ui.screens.note_list.components.DailyTaskItem
import academy.apparchitects.notesapp.ui.screens.note_list.components.RecentNotesItem
import academy.apparchitects.notesapp.ui.screens.note_list.components.ReminderItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotesListSuccess(
  recentNotes: List<Note>,
  dailyTasks: List<DailyTask>,
  reminders: List<Note>,
  onNoteClick: (String, Note) -> Unit,
  modifier: Modifier = Modifier,
) {

  LazyVerticalStaggeredGrid(
    modifier = modifier.padding(horizontal = 16.dp).fillMaxSize(),
    columns = StaggeredGridCells.Fixed(2),
    verticalItemSpacing = 8.dp,
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    content = {

      item(span = StaggeredGridItemSpan.FullLine) {
        Text(
          "Fav Notes",
          textDecoration = TextDecoration.Underline,
          fontWeight = FontWeight.Bold
        )
        Divider()
      }

      item(span = StaggeredGridItemSpan.FullLine) {
        Text("Reminder", fontWeight = FontWeight.Bold, fontSize = 24.sp)
      }

      item(span = StaggeredGridItemSpan.FullLine) {
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
        ) {
          items(reminders) {
            it.title?.let { title ->
              ReminderItem(
                title = title,
                onClick = {
                  onNoteClick(
                    it.id.toString(),
                    // TODO: This is hacky for now to pass the note object to the details screen
                    // Change it once repository layer is there
                    it
                  )
                }
              )
            }
          }
        }
      }

      item(span = StaggeredGridItemSpan.FullLine) {
        Text(
          "Recent Notes",
          fontWeight = FontWeight.Bold,
          fontSize = 24.sp,
          modifier = Modifier.padding(top = 10.dp)
        )
      }

      item(span = StaggeredGridItemSpan.FullLine) {
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
        ) {
          items(recentNotes) { item ->
            RecentNotesItem(
              title = item.title ?: "",
              desc = item.desc ?: "",
              content = item.note,
              modifier = Modifier,
              onNoteClick = {
                onNoteClick(
                  item.id.toString(),
                  // TODO: This is hacky for now to pass the note object to the details screen
                  // Change it once repository layer is there
                  item
                )
              }
            )
          }
        }
      }

      item(span = StaggeredGridItemSpan.FullLine) {
        Text(
          "Daily Tasks",
          fontWeight = FontWeight.Bold,
          fontSize = 24.sp,
          modifier = Modifier.padding(top = 10.dp)
        )
      }

      items(dailyTasks) { item ->
        DailyTaskItem(
          dailyTask = item
        )
      }
    }
  )
}