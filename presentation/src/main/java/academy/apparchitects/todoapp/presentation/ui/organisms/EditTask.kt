package academy.apparchitects.todoapp.presentation.ui.organisms

import academy.apparchitects.todoapp.presentation.ui.atoms.CustomButton
import academy.apparchitects.todoapp.presentation.ui.molecules.DatePickerField
import academy.apparchitects.todoapp.presentation.ui.molecules.TaskForm
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
    onDateChange: (String) -> Unit,
    onUpdateClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        TaskForm(title = title, detail = detail, onTitleChange = onTitleChange, onDetailChange = onDetailChange)
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField{ selectedDate ->
            onDateChange (selectedDate)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CustomButton(text = "Update", onClick = onUpdateClick)
            CustomButton(text = "Cancel", onClick = onCancelClick)
        }
    }
}
