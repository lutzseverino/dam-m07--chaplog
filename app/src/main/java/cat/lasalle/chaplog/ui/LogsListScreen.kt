package cat.lasalle.chaplog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cat.lasalle.chaplog.data.LogUiState
import cat.lasalle.chaplog.data.LogsUiState

@Composable
fun LogsListScreen(
    logsUiState: LogsUiState,

    onLogClicked: (LogUiState) -> Unit,
    onLogLongClicked: (LogUiState) -> Unit
) {
    Column {
        logsUiState.logs.forEach { log ->
            ListItem(
                headlineContent = { Text(text = "${log.title} by ${log.author}") },
                supportingContent = { Text(text = "${log.currentPage} / ${log.pages}") },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit log"
                    )
                },
            )
        }
    }
}
