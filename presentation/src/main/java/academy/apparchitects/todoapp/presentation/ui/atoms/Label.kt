package academy.apparchitects.todoapp.presentation.ui.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Label(
    modifier: Modifier =  Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black,
    fontSize: Int = 18
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle.Default.copy(
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            color = color
        )
    )
}