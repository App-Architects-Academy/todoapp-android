package academy.apparchitects.todoapp.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
  val regular: Shape,
  val circular: Shape
)

val appShapes = AppShapes(
  regular = RoundedCornerShape(15.dp),
  circular = CircleShape
)

val LocalAppShapes = staticCompositionLocalOf { appShapes }