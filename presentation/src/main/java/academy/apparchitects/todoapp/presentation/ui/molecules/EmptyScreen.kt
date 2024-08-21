package academy.apparchitects.todoapp.presentation.ui.molecules

import academy.apparchitects.todoapp.presentation.ui.atoms.Label
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


/**
* Written by Vikram Ragu on 21/08/24.
*/

@Composable
fun EmptyScreen(
    modifier: Modifier,
    labelText : String,
    iconTint : Color,
    textColor : Color
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {

            Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = null,
                tint = iconTint
            )

            Spacer(modifier = Modifier.size(16.dp))

            Label(
                text = labelText,
                fontWeight = FontWeight.W500,
                color = textColor,
                fontSize = 18
            )
        }
    )
}