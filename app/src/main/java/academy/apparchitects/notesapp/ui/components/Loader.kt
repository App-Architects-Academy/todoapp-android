package academy.apparchitects.notesapp.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BoxScope.Loader() {
  CircularProgressIndicator(
    modifier = Modifier.align(Alignment.Center)
  )
}