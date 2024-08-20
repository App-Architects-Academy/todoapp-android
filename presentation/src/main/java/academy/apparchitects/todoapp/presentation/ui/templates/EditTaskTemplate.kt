package academy.apparchitects.todoapp.presentation.ui.templates

import academy.apparchitects.todoapp.presentation.ui.organisms.AddEditTask
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskTemplate(
    toolBarTitle: String,
    isEditMode: Boolean = true,
    title: String,
    detail: String,
    category: String,
    priority: String,
    onPriorityChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onDetailChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onUpdateClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(toolBarTitle) },
                navigationIcon = {
                    IconButton(onClick = { /* todo Handle back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            AddEditTask(
                isEditMode = isEditMode,
                title = title,
                detail = detail,
                onTitleChange = onTitleChange,
                onDetailChange = onDetailChange,
                onUpdateClick = onUpdateClick,
                onCancelClick = onCancelClick,
                onDateChange = onDateChange,
                onCategoryChange = onCategoryChange,
                category = category,
                priority = priority,
                onPriorityChange = onPriorityChange
            )
        }
    }
}

