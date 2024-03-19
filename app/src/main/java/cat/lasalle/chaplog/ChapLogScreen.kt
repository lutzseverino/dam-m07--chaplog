package cat.lasalle.chaplog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cat.lasalle.chaplog.data.BookLog
import cat.lasalle.chaplog.database.repository.BookLogRepository
import cat.lasalle.chaplog.model.BookLogListViewModel
import cat.lasalle.chaplog.ui.LogAddScreen
import cat.lasalle.chaplog.ui.LogScreen
import cat.lasalle.chaplog.ui.LogsListScreen
import java.util.logging.Logger

enum class ChapLogScreen(val title: String) {
    Start("ChapLog"),
    Create("Add new log"),
    Read("Inspecting log"),
    Update("Update log"),
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ChapLogAppBar(
    currentScreen: ChapLogScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(modifier = modifier,

        title = { Text(text = currentScreen.title) },

        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),

        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigate up"
                    )
                }
            }
        }
    )
}

@Composable
fun ChapLogApp(
    viewModel: BookLogListViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    bookLogRepository: BookLogRepository
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        ChapLogScreen.valueOf(backStackEntry?.destination?.route ?: ChapLogScreen.Start.name)

    Scaffold(
        topBar = {
            ChapLogAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier.fillMaxWidth()
            )
        },

        floatingActionButton = {
            if (currentScreen == ChapLogScreen.Start) {
                FloatingActionButton(
                    onClick = { navController.navigate(ChapLogScreen.Create.name) },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add"
                        )
                    }
                )
            }
        }

    ) { padding ->
        val uiState by viewModel.uiState.collectAsState()
        Logger.getAnonymousLogger().info("uiState: ${uiState.logs.count()}")

        NavHost(
            navController = navController,
            startDestination = ChapLogScreen.Start.name,
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            composable(route = ChapLogScreen.Start.name) {
                LogsListScreen(
                    logs = uiState.logs,
                    onLogClicked = {
                        viewModel.focus(it)
                        navController.navigate(ChapLogScreen.Read.name)
                    },
                    onLogDelete = {
                        viewModel.remove(it)
                    }
                )
            }

            composable(route = ChapLogScreen.Create.name) {
                LogAddScreen(
                    initialLog = BookLog(),
                    onAdd = {
                        viewModel.add(it)
                        navController.navigateUp()
                    },
                    onCancel = { navController.navigateUp() }
                )
            }

            composable(route = ChapLogScreen.Read.name) {
                LogScreen(
                    log = uiState.focusedLog!!,
                    onEdit = {
                        viewModel.focus(it)
                        navController.navigate(ChapLogScreen.Update.name)
                    }
                )
            }

            composable(route = ChapLogScreen.Update.name) {
                LogAddScreen(
                    initialLog = uiState.focusedLog!!,
                    onAdd = {
                        viewModel.remove(uiState.focusedLog!!)
                        viewModel.add(it)
                        viewModel.focus(it)

                        navController.navigateUp()
                    },
                    onCancel = { navController.navigateUp() }
                )

            }
        }
    }
}
