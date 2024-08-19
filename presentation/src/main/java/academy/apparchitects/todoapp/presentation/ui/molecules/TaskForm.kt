package academy.apparchitects.todoapp.presentation.ui.molecules

import academy.apparchitects.todoapp.presentation.ui.atoms.CustomTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskForm(
    title: String,
    detail: String,
    onTitleChange: (String) -> Unit,
    onDetailChange: (String) -> Unit
) {
    Column {
        CustomTextField(label = "Title", value = title, onValueChange = onTitleChange)
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(label = "Detail", value = detail, onValueChange = onDetailChange)
    }
}
