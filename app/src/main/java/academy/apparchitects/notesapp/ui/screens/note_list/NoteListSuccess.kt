package academy.apparchitects.notesapp.ui.screens.note_list

import academy.apparchitects.notesapp.data.DailyTask
import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.ui.screens.note_list.components.DailyTaskItem
import academy.apparchitects.notesapp.ui.screens.note_list.components.RecentNotesItem
import academy.apparchitects.notesapp.ui.screens.note_list.components.ReminderItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
// -------------------------------------------------
// Reminders Section
// -------------------------------------------------
  LazyColumn(
    modifier = modifier.padding(horizontal = 16.dp)
  ) {
    item {
      Text("Reminders", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
    item {
      LazyRow(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 16.dp)
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

// -------------------------------------------------
// Recent Notes Section
// -------------------------------------------------
    item {
      Text("Recent Notes", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
    item {
      LazyRow(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 16.dp)
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

// -------------------------------------------------
// Daily Tasks Section
// -------------------------------------------------
    item {
      Text(
        "Daily Tasks",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
      )
    }
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 16.dp),
        content = {
          val resultEven = dailyTasks.filterIndexed { index, _ ->
            index % 2 == 0
          }
          val resultOdd = dailyTasks.filterIndexed { index, _ ->
            index % 2 != 0
          }

          Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            resultEven.forEach { item ->
              DailyTaskItem(
                modifier = Modifier,
                dailyTask = item
              )
            }
          }
          Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
          ) {
            resultOdd.forEach { item ->
              DailyTaskItem(
                modifier = Modifier,
                dailyTask = item
              )
            }
          }
        }
      )

      /*LazyVerticalStaggeredGrid(
          columns = StaggeredGridCells.Fixed(2)
      ) {

      }*/
    }
    /*item {
        FlowColumn(
            modifier = Modifier,
            maxItemsInEachColumn = 2
        ) {
            dailyTasks.forEach { item ->
                DailyTaskItem(
                    modifier = Modifier,
                    dailyTask = item
                )
            }
        }
    }*/
    /*items(favNotes) { note ->
        Card(onClick = { onNoteClick(note.id.toString()) }) {
            Column {
                Text(text = note.id.toString())
                Text(text = note.title ?: "No Title")
                Text(text = note.note)
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
    item {
        Text("Other Notes",
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        )
        Divider()
    }
    items(otherNotes) { note ->
        Card(onClick = { onNoteClick(note.id.toString()) }) {
            Column {
                Text(text = note.id.toString())
                Text(text = note.title ?: "No Title")
                Text(text = note.note)
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }*/
  }
}