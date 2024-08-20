package academy.apparchitects.todoapp.presentation.ui.pages

import academy.apparchitects.todoapp.presentation.ui.templates.EditTaskTemplate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddEditTaskPage(isEditMode: Boolean,toolBarTitle: String = "") {
    var title by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }

    EditTaskTemplate(
        isEditMode = isEditMode,
        toolBarTitle = toolBarTitle,
        title = title,
        detail = detail,
        category = category,
        priority = priority,
        onTitleChange = { title = it },
        onDetailChange = { detail = it },
        onCategoryChange = { category = it },
        onPriorityChange = { priority = it },
        onUpdateClick = { /*todo Handle update */ },
        onCancelClick = { /*todo Handle cancel */ },
        onDateChange = { dueDate = it }
    )
}

@Preview
@Composable
private fun PrevEditTaskPage() {
    AddEditTaskPage(isEditMode = true,toolBarTitle = "Edit Task")
}

@Preview
@Composable
private fun PrevAddTaskPage() {
    AddEditTaskPage(isEditMode = false,toolBarTitle = "Add Task")
}