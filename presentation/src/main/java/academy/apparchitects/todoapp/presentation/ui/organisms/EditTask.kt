package academy.apparchitects.todoapp.presentation.ui.organisms

import academy.apparchitects.todoapp.presentation.ui.atoms.TitleText
import academy.apparchitects.todoapp.presentation.ui.molecules.FormButtons
import academy.apparchitects.todoapp.presentation.ui.molecules.TaskForm
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditTask(
    title: String,
    detail: String,
    onTitleChange: (String) -> Unit,
    onDetailChange: (String) -> Unit,
    onUpdateClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        TitleText(text = "Edit Task")
        Spacer(modifier = Modifier.height(32.dp))
        TaskForm(title = title, detail = detail, onTitleChange = onTitleChange, onDetailChange = onDetailChange)
        Spacer(modifier = Modifier.height(32.dp))
        FormButtons(onUpdateClick = onUpdateClick, onCancelClick = onCancelClick)
    }
}
