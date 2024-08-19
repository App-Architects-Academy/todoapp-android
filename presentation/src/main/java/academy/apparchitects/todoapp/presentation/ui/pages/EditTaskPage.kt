package academy.apparchitects.todoapp.presentation.ui.pages

import academy.apparchitects.todoapp.presentation.ui.templates.EditTaskTemplate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditTaskPage() {
    var title by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    EditTaskTemplate(
        title = title,
        detail = detail,
        onTitleChange = { title = it },
        onDetailChange = { detail = it },
        onUpdateClick = { /* Handle update */ },
        onCancelClick = { /* Handle cancel */ },
        onDateChange = { dueDate = it}
    )
}

@Preview
@Composable
private fun PrevEditTaskPage() {
    EditTaskPage()
}