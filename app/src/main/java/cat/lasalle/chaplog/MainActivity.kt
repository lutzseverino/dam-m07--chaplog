package cat.lasalle.chaplog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cat.lasalle.chaplog.ui.theme.Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                ChapLogApp()
            }
        }
    }

}