package academy.apparchitects.todoapp.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
  // Primary
  val primary: Color = Color(0xFF9395D3),
  val primaryLight: Color = Color(0xFFB3B7EE),

  // Secondary
  val secondary: Color = Color(0xFF8B8787),

  val grey : Color = Color(0XFFE9EAEC),

  // Others
  val white: Color = Color(0xFFFFFFFF),
  val black: Color = Color(0xFF000000),

  // Dark Colors
  val dark1: Color = Color(0xFF181A20),
  val dark2: Color = Color(0xFF1F222A),
  val dark3: Color = Color(0xFF35383F),

  // Background
  val primaryBg: Color = Color(0xFFD6D7EF),
)

val lightAppColors = AppColors()

val darkAppColors = lightAppColors.copy(
  primary = Color(0xFFB3B7EE),
  primaryLight = Color(0xFF9395D3),
  secondary = Color(0xFF8B8787), // Neutral color
  white = Color(0xFF181A20),
  black = Color(0xFFFFFFFF),
  primaryBg = Color(0xFF1F222A)
)

fun getColors(
  darkTheme: Boolean,
): AppColors {
  return if (darkTheme) {
    darkAppColors
  } else {
    lightAppColors
  }
}