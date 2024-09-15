package academy.apparchitects.todoapp.ui.addedit

import academy.apparchitects.todoapp.presentation.ui.templates.EditTaskTemplate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddEditTaskScreen(
    isEditMode: Boolean,
    toolBarTitle: String
) {

    val title = remember { mutableStateOf("") }
    val detail = remember { mutableStateOf("") }
    val dueDate = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val priority = remember { mutableStateOf("") }

    EditTaskTemplate(
        isEditMode = isEditMode,
        toolBarTitle = toolBarTitle,
        title = title.value,
        detail = detail.value,
        category = category.value,
        priority = priority.value,
        onTitleChange = { title.value = it },
        onDetailChange = { detail.value = it },
        onCategoryChange = { category.value = it },
        onPriorityChange = { priority.value = it },
        onUpdateClick = {  },
        onCancelClick = {  },
        onDateChange = { dueDate.value = it }
    )
}

@Preview
@Composable
private fun PrevEditTaskPage() {
    //AddEditTaskPage(isEditMode = true,toolBarTitle = "Edit Task")
}

@Preview
@Composable
private fun PrevAddTaskPage() {
    //AddEditTaskPage(isEditMode = false,toolBarTitle = "Add Task")
}