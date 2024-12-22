package ca.appfactoryto.itemlistviewer.ui.app

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.appfactoryto.itemlistviewer.data.EmbeddedItemRepository
import ca.appfactoryto.itemlistviewer.data.RemoteItemRepository
import ca.appfactoryto.itemlistviewer.domain.ItemListRepository
import ca.appfactoryto.itemlistviewer.ui.groupedlist.GroupedListViewModel
import ca.appfactoryto.itemlistviewer.ui.simplelist.FlatListViewModel

/**
 * ViewModel for ItemListViewerApp. Stores the entire state of the app.
 */
class ItemListViewerViewModel : ViewModel() {

    /**
     * The repository to use for fetching items.
     */
    private val repository: ItemListRepository = RemoteItemRepository // EmbeddedItemRepository

    /**
     * The state of the snackbar.
     */
    val snackbarHostState = SnackbarHostState()

    /**
     * The currently selected tab.
     */
    val selectedTab: MutableState<Int> = mutableIntStateOf(0)

    /**
     * The currently selected sort rule.
     */
    val selectedSortRule = mutableStateOf(SortRule.NUMERICAL)

    /**
     * View model for the flat list view.
     */
    val flatViewModel: FlatListViewModel = FlatListViewModel(
        repository, selectedSortRule.value,
        snackbarHostState
    )

    /**
     * View model for the grouped list view.
     */
    val groupedViewModel: GroupedListViewModel = GroupedListViewModel(
        repository, selectedSortRule.value,
        snackbarHostState
    )

    companion object {
        const val FLAT_LIST_TAB_INDEX = 0
        const val GROUPED_LIST_TAB_INDEX = 1
    }

    /**
     * Callback when the sort rule is changed.
     */
    val onSortRuleChanged: (SortRule) -> Unit = {
        selectedSortRule.value = it
    }

    /**
     * Callback when the flat list tab is selected.
     */
    val onFlatListSelected = {
        selectedTab.value = FLAT_LIST_TAB_INDEX
    }

    /**
     * Callback when the grouped list tab is selected.
     */
    val onGroupedListSelected = {
        selectedTab.value = GROUPED_LIST_TAB_INDEX
    }
}