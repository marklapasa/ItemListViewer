package ca.appfactoryto.itemlistviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModelProvider
import ca.appfactoryto.itemlistviewer.ui.app.ItemListViewerApp
import ca.appfactoryto.itemlistviewer.ui.app.ItemListViewerViewModel
import ca.appfactoryto.itemlistviewer.ui.theme.ItemListViewerTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private lateinit var model: ItemListViewerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this)[ItemListViewerViewModel::class.java]

        enableEdgeToEdge()

        // Render app
        setContent {
            ItemListViewerTheme {
                ItemListViewerApp(model = model)
            }
        }
    }
}