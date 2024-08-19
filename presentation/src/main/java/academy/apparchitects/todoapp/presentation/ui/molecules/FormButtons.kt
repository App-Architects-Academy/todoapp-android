package academy.apparchitects.todoapp.presentation.ui.molecules

import academy.apparchitects.todoapp.presentation.ui.atoms.CustomButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FormButtons(onUpdateClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        CustomButton(text = "Update", onClick = onUpdateClick)
        CustomButton(text = "Cancel", onClick = onCancelClick)
    }
}