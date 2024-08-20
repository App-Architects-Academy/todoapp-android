package academy.apparchitects.todoapp.presentation.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    containerColor: Color = Color(0xFF9575CD),
    contentColor: Color = Color.White,
    cornerRadius: Int = 8,
    disabledContainerColor: Color = Color.Gray,
    disabledContentColor: Color = Color.DarkGray,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .background(color = containerColor, shape = RoundedCornerShape(cornerRadius.dp)),
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
