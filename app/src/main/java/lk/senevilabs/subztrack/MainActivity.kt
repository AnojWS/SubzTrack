package lk.senevilabs.subztrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import lk.senevilabs.subztrack.features.navigation.AppNavigationBar
import lk.senevilabs.subztrack.ui.theme.SubzTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubzTrackTheme {
                AppNavigationBar()
            }
        }
    }
}

