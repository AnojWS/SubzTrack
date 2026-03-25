package lk.senevilabs.subztrack.features.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    DASHBOARD("dashboard", "Dashboard", Icons.Default.Info, "Dashboard")

}

@Composable
fun Navigation() {
    val navController = rememberNavController()
}