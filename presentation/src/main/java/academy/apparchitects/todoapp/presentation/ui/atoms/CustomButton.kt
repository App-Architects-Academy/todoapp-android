package academy.apparchitects.todoapp.presentation.ui.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = Color(0xFF9575CD),
    contentColor: Color = Color.White,
    disabledContainerColor: Color = Color.Gray,
    disabledContentColor: Color = Color.DarkGray
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    ) {
        Text(text = text)
    }
}
