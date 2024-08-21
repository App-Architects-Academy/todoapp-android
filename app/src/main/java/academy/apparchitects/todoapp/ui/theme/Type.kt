package academy.apparchitects.todoapp.ui.theme

import academy.apparchitects.todoapp.R
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

private val provider = GoogleFont.Provider(
  providerAuthority = "com.google.android.gms.fonts",
  providerPackage = "com.google.android.gms",
  certificates = R.array.com_google_android_gms_fonts_certs,
)

val jost: FontFamily = try {
  val font = GoogleFont("Jost")

  FontFamily(
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Normal),
  )

} catch (e: Exception) {
  FontFamily.Default
}

data class AppTypography(
  val h1SemiBold: TextStyle,
  val h1Regular: TextStyle,
  val h2SemiBold: TextStyle,
  val h2Regular: TextStyle,
  val h3SemiBold: TextStyle,
  val h3Regular: TextStyle,
  val h4SemiBold: TextStyle,
  val h4Regular: TextStyle,
)

val appTypography = AppTypography(
  h1SemiBold = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp
  ),
  h1Regular = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp
  ),
  h2SemiBold = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp
  ),
  h2Regular = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),
  h3SemiBold = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp
  ),
  h3Regular = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp
  ),
  h4SemiBold = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.SemiBold,
    fontSize = 10.sp
  ),
  h4Regular = TextStyle(
    fontFamily = jost,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp
  )
)

val LocalAppTypography = staticCompositionLocalOf { appTypography }