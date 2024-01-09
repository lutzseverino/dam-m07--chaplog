package cat.lasalle.chaplog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.lasalle.chaplog.data.LogUiState

@Composable
fun LogAddScreen(
    onAdd: (LogUiState) -> Unit,
    onCancel: () -> Unit
) {
    val author = rememberSaveable { mutableStateOf("") }
    val title = rememberSaveable { mutableStateOf("") }

    // TODO: Fix modifiers and add all fields.

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") },
        )

        TextField(
            value = author.value,
            onValueChange = { author.value = it },
            label = { Text("Author") },
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilledTonalButton(onClick = { onCancel() }) {
                Text("Cancel")
            }

            Button(onClick = { onAdd(LogUiState(title = title.value, author = author.value)) }) {
                Text("Save")
            }
        }
    }
}
