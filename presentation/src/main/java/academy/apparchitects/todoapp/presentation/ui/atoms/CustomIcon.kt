package academy.apparchitects.todoapp.presentation.ui.atoms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


/**
 * Written by Vikram Ragu on 21/08/24.
 */
@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    size : Int,
    color: Color
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = modifier.size(size.dp),
        tint = color
    )
}