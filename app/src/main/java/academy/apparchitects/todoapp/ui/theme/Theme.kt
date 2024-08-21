package academy.apparchitects.todoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
data class DarkTheme(val isDark: Boolean = false)

val LocalTheme = staticCompositionLocalOf { DarkTheme() }

val LocalAppColors = staticCompositionLocalOf {
  getColors(false)
}

@Composable
fun NotesAppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val theme = DarkTheme(darkTheme)
  val colors = getColors(darkTheme)

  CompositionLocalProvider(
    LocalTheme provides theme,
    LocalAppColors provides colors,
    LocalAppTypography provides appTypography,
    LocalAppShapes provides appShapes
  ) {
    MaterialTheme(
      content = content
    )
  }
}


object AppTheme {

  val typography: AppTypography
    @Composable
    get() = LocalAppTypography.current

  val colors: AppColors
    @Composable
    get() = LocalAppColors.current

  val theme: DarkTheme
    @Composable
    get() = LocalTheme.current

  val shapes: AppShapes
    @Composable
    get() = LocalAppShapes.current

}