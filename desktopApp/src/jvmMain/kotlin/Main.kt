import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.jettaskboard.multiplatform.MainView

fun main() = application {
    Window(
        title = "JetTaskBoardKMP",
        state = rememberWindowState(width = 1200.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
    ) {
        MainView()
    }
}