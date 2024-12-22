package ca.appfactoryto.itemlistviewer.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.Serializable

/**
 * A map of items, keyed by listId as the group Id .
 */
@Serializable
class GroupedItem(

    /**
     * The list id shared by all items in the group.
     */
    val groupId: Int,

    /**
     * The list of items in the group.
     */
    val items: List<Item>,
) {

    /**
     * UI-related state. Whether the group is expanded or not.
     */
    var isExpanded: MutableState<Boolean> = mutableStateOf(false)

    /**
     * Return a list of items chunked into lists of the specified size.
     */
    fun getChunkedItems(chunkSize: Int): List<List<Item>> {
        return items.chunked(chunkSize)
    }
}