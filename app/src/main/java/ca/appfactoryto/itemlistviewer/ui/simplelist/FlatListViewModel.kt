package ca.appfactoryto.itemlistviewer.ui.simplelist

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.appfactoryto.itemlistviewer.domain.Item
import ca.appfactoryto.itemlistviewer.domain.ItemListRepository
import ca.appfactoryto.itemlistviewer.ui.SortUtil
import ca.appfactoryto.itemlistviewer.ui.app.SortRule
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/**
 * ViewModel for FlatListView.
 */
class FlatListViewModel(
    private val repository: ItemListRepository,
    private val sortRule: State<SortRule>,
    private val snackbarHostState: SnackbarHostState,
) : ViewModel() {

    val items = mutableStateListOf<Item>()

    val listState: LazyListState = LazyListState()

    fun onItemClicked(item: Item) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(
                message = item.toString()
            )
        }
    }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                items.clear()
                items.addAll(SortUtil.prepare(repository.fetchAll(), sortRule.value))
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