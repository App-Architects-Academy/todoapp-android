package academy.apparchitects.todoapp.presentation.ui.organisms

import academy.apparchitects.todoapp.presentation.ui.atoms.CustomButton
import academy.apparchitects.todoapp.presentation.ui.molecules.CustomSelector
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
fun AddEditTask(
    isEditMode: Boolean,
    title: String,
    detail: String,
    category: String,
    priority: String,
    onTitleChange: (String) -> Unit,
    onDetailChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onUpdateClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        TaskForm(
            title = title,
            detail = detail,
            onTitleChange = onTitleChange,
            onDetailChange = onDetailChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField { selectedDate ->
            onDateChange(selectedDate)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CategorySelector(category, onCategoryChange)

        Spacer(modifier = Modifier.height(16.dp))
        PrioritySelector(priority, onPriorityChange)

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isEditMode) {
                CustomButton(
                    text = "Update",
                    onClick = onUpdateClick,
                    modifier = Modifier.weight(1f)
                )
                CustomButton(
                    text = "Cancel",
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                )
            } else {
                CustomButton(
                    text = "Add",
                    onClick = onUpdateClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

@Composable
private fun CategorySelector(category: String, onCategoryChange: (String) -> Unit) {
    CustomSelector(
        selectedCategory = category,
        onCategorySelected = onCategoryChange,
        categories = listOf("Work", "Personal", "Shopping", "Others"),
        selectorHint = "Select Category"
    )
}

@Composable
private fun PrioritySelector(category: String, onCategoryChange: (String) -> Unit) {
    CustomSelector(
        selectedCategory = category,
        onCategorySelected = onCategoryChange,
        categories = listOf("High", "Medium", "Low"),
        selectorHint = "Select Priority"
    )
}

