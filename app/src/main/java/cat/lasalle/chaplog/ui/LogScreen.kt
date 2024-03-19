package cat.lasalle.chaplog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.lasalle.chaplog.data.BookLog

@Composable
fun LogScreen(
    log: BookLog,
    onEdit: (BookLog) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = log.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "Author: ${log.author}", fontSize = 16.sp)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = "Current Page", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = log.currentPage.toString(), fontSize = 14.sp)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = "Total Pages", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = log.pages.toString(), fontSize = 14.sp)
        }

        Button(onClick = {
            onEdit(log)
        }) {
            Text("Edit")
        }
    }
}