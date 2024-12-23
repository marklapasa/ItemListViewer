package ca.appfactoryto.itemlistviewer.ui.groupedlist

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.appfactoryto.itemlistviewer.domain.GroupedItem
import ca.appfactoryto.itemlistviewer.domain.Item
import ca.appfactoryto.itemlistviewer.domain.ItemListRepository
import ca.appfactoryto.itemlistviewer.ui.SortUtil
import ca.appfactoryto.itemlistviewer.ui.app.SortRule
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/**
 * The ViewModel for the GroupedListScreen.
 */
class GroupedListViewModel(
    private val repository: ItemListRepository,
    private val sortRule: State<SortRule>,
    private val snackbarHostState: SnackbarHostState
) : ViewModel() {

    /**
     * The list of grouped items to be displayed in the UI.
     */
    val groupedItems = mutableStateListOf<GroupedItem>()

    /**
     * The state of the LazyList used to display the grouped items.
     */
    val listState: LazyListState = LazyListState()

    /**
     * When the User taps on an item, it will show a snackbar with the item's details
     */
    fun onItemClicked(item: Item) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(
                message = item.toString()
            )
        }
    }

    /**
     * When the User taps on the group header, it will show the items that belong to that group,
     * display in n-columns
     */
    val sublistColumns = 3 // 3 is ideal for portrait mode but for landscape mode it could be more

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                groupedItems.clear()
                groupedItems.addAll(
                    SortUtil.prepare(repository.fetchAll(), sortRule.value).let {
                        it.groupBy { it.listId }
                            .map { (listId, items) ->
                                GroupedItem(listId, items)
                            }
                    }
                )
            } catch (e: Exception) {
                if (e is UnknownHostException) {
                    snackbarHostState.showSnackbar(
                        message = "No internet connection"
                    )
                } else {
                    snackbarHostState.showSnackbar(
                        message = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}