package academy.apparchitects.todoapp.presentation.ui.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Label(text: String) {
    Text(
        text = text,
        style = TextStyle.Default.copy(
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = Color.LightGray
        )
    )
}