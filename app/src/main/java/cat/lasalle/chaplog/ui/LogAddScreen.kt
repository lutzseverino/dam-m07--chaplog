package cat.lasalle.chaplog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import cat.lasalle.chaplog.data.LogUiState

fun validateNumber(number: String): Boolean {
    return number != "" && number.isDigitsOnly()
}

@Composable
fun LogAddScreen(
    onAdd: (LogUiState) -> Unit,
    onCancel: () -> Unit
) {
    val author = rememberSaveable { mutableStateOf("") }
    val title = rememberSaveable { mutableStateOf("") }
    val currentPage = rememberSaveable { mutableIntStateOf(0) }
    val pages = rememberSaveable { mutableIntStateOf(0) }

    // TODO: Fix modifiers and add all fields.

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

                value = title.value,
                onValueChange = { title.value = it },
                label = { Text("Title") }
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

                value = author.value,
                onValueChange = { author.value = it },
                label = { Text("Author") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    singleLine = true,

                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = !validateNumber(currentPage.intValue.toString()),

                    value = currentPage.intValue.toString(),
                    onValueChange = {
                        if (validateNumber(it)) {
                            currentPage.intValue = it.toInt()
                        }
                    },
                    label = { Text("Current page") }
                )

                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    singleLine = true,

                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = !validateNumber(pages.intValue.toString()),

                    value = pages.intValue.toString(),
                    onValueChange = {
                        if (validateNumber(it)) {
                            pages.intValue = it.toInt()
                        }
                    },
                    label = { Text("Total pages") }
                )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilledTonalButton(onClick = { onCancel() }) {
                Text("Cancel")
            }

            Button(onClick = {
                onAdd(
                    LogUiState(
                        title = title.value,
                        author = author.value,
                        currentPage = currentPage.intValue,
                        pages = pages.intValue
                        )
                )
            }) {
                Text("Save")
            }
        }
    }
}
