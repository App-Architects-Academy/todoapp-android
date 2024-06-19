package academy.apparchitects.notesapp.ui.screens

import academy.apparchitects.notesapp.data.DailyTask
import academy.apparchitects.notesapp.data.Note
import academy.apparchitects.notesapp.data.TodoItem
import academy.apparchitects.notesapp.presentation.noteslist.NotesListStates
import academy.apparchitects.notesapp.presentation.noteslist.NotesListVM
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NotesListScreen(
    onNoteClick: (String) -> Unit,
    notesListVM: NotesListVM = hiltViewModel()
) {

    val state = notesListVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        notesListVM.fetchNotes()
    }

    val currentState = state.value
    when (currentState) {
        is NotesListStates.Success -> {
            NotesListSuccess(
                recentNotes = currentState.recentNotes,
                dailyTasks = currentState.dailyTasks,
                reminders = currentState.reminders,
                onNoteClick = onNoteClick
            )
        }

        is NotesListStates.Error -> {
            Text(text = currentState.errorDetails)
        }

        is NotesListStates.Idle -> {
            Text(text = "Nothing happenning")
        }

        is NotesListStates.Loading -> {
            Text(text = "Loading")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NotesListSuccess(
    recentNotes: List<Note>,
    dailyTasks: List<DailyTask>,
    reminders: List<Note>,
    onNoteClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                "Fav Notes",
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
            Divider()
        }

        item {
            Text("Reminder", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                items(reminders) {
                    it.title?.let { title -> ReminderItem(title = title) }
                }
            }
        }

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
                        modifier = Modifier
                    )
                }
            }
        }


        item {
            Text(
                "Daily Tasks",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
        }


        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                content = {

                    val resultEven = dailyTasks.filterIndexed { index, dailyTask ->
                        index % 2 == 0
                    }

                    val resultOdd = dailyTasks.filterIndexed { index, dailyTask ->
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
                        //Spacer(modifier = Modifier.height(4.dp))
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
                        //Spacer(modifier = Modifier.height(4.dp))
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


@Composable
fun ReminderItem(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(end = 8.dp)
            .background(Color(0xFFF3EBA9))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = Color.White)
    }
}

@Composable
fun RecentNotesItem(
    title: String,
    desc: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.5f)
            .padding(end = 8.dp)
            .background(Color(0xFFF3EBA9))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column {
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


@Composable
fun DailyTaskItem(
    dailyTask: DailyTask,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(end = 8.dp)
            .background(Color(0xFFF3EBA9))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
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

