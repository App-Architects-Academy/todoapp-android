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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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

@Composable
fun NotesListSuccess(
    recentNotes: List<Note>,
    dailyTasks: List<DailyTask>,
    reminders: List<Note>,
    onNoteClick: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
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
                        it.title?.let { title -> ReminderItem(title = title) }
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
                            modifier = Modifier
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

            itemsIndexed(dailyTasks) { index, item ->
                DailyTaskItem(
                    dailyTask = item,
                    index = index
                )
            }
        }
    )
}


@Composable
fun ReminderItem(
    title: String,
    modifier: Modifier = Modifier
) {
    Row {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFF3EBA9))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, color = Color.Black)
        }
        Spacer(modifier = Modifier.width(6.dp))
    }
}

@Composable
fun RecentNotesItem(
    title: String,
    desc: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Row {
        Box(
            modifier = modifier
                .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.5f)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFF3EBA9))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = desc,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    color = Color.Gray,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
    }
}


@Composable
fun DailyTaskItem(
    dailyTask: DailyTask,
    modifier: Modifier = Modifier,
    index: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFF3EBA9))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier.weight(1f)) {
                Text(
                    text = dailyTask.title ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp
                )
                dailyTask.todoItems.forEach { item ->
                    TodoListItem(
                        todoItem = item,
                        onCheckedChange = { a, b ->
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(26.dp))
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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
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
        Text(
            text = todoItem.item,
            color = Color.Gray,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp
        )
    }
}