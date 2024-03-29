package cat.lasalle.chaplog.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cat.lasalle.chaplog.data.BookLog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogsListScreen(
    logs: List<BookLog>,

    onLogClicked: (BookLog) -> Unit,
    onLogDelete: (BookLog) -> Unit,
) {
    val openDeleteDialog = remember { mutableStateOf(false) }
    val logToDelete = remember { mutableStateOf(BookLog()) }

    Column {
        when {
            logs.isEmpty() -> {
                Text(text = "No logs yet, add one by clicking the button below.")
            }
        }

       logs.forEach { log ->
            ListItem(
                modifier = Modifier.combinedClickable(
                    onClick = { onLogClicked(log) },
                    onLongClick = {
                        logToDelete.value = log
                        openDeleteDialog.value = true
                    }
                ),

                headlineContent = { Text(text = log.title) },
                supportingContent = { Text(text = "${log.author} · ${log.currentPage} / ${log.pages}") },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Edit log",
                    )
                },
            )
            Divider()
        }

        when {
            openDeleteDialog.value -> {
                LogDeleteDialog(
                    log = logToDelete.value,
                    onConfirmation = {
                        onLogDelete(logToDelete.value)
                        openDeleteDialog.value = false
                    },
                    onDismissRequest = {
                        openDeleteDialog.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun LogDeleteDialog(
    log: BookLog,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "Delete this log?")
        },
        text = {
            Text(text = "Are you sure you want to delete ${log.title}?")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )

}
