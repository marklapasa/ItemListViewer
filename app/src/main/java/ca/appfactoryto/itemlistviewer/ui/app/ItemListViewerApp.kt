package ca.appfactoryto.itemlistviewer.ui.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.appfactoryto.itemlistviewer.R
import ca.appfactoryto.itemlistviewer.ui.app.ItemListViewerViewModel.Companion.FLAT_LIST_TAB_INDEX
import ca.appfactoryto.itemlistviewer.ui.app.ItemListViewerViewModel.Companion.GROUPED_LIST_TAB_INDEX
import ca.appfactoryto.itemlistviewer.ui.groupedlist.GroupedListView
import ca.appfactoryto.itemlistviewer.ui.simplelist.FlatListView

/**
 * Main screen of the app that hosts 2 types of lists to view the same data.
 */
@ExperimentalMaterial3Api
@Composable
fun ItemListViewerApp(model: ItemListViewerViewModel) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(2.dp),
                title = { Text(stringResource(R.string.item_list_viewer)) },
                actions = {
                    MoreMenuButton(
                        modifier = Modifier,
                        onSortRuleChanged = model.onSortRuleChanged,
                        sortRule = model.selectedSortRule.value
                    )
                }
            )
        },
        content = { innerPadding ->
            when (model.selectedTab.value) {
                FLAT_LIST_TAB_INDEX -> {
                    FlatListView(
                        modifier = Modifier.padding(innerPadding),
                        model = model.flatViewModel
                    )
                }

                GROUPED_LIST_TAB_INDEX -> {
                    GroupedListView(
                        modifier = Modifier.padding(innerPadding),
                        model = model.groupedViewModel
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(model.snackbarHostState)
        },
        bottomBar = {
            ItemListViewerNavigation(
                selectedTabIndex = model.selectedTab.value,
                onFlatListSelected = model.onFlatListSelected,
                onGroupedListSelected = model.onGroupedListSelected
            )
        })
}

