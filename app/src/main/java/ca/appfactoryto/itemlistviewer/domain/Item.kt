package ca.appfactoryto.itemlistviewer.domain

import kotlinx.serialization.Serializable

/**
 * A data class representing an item in a list.
 */
@Serializable
data class Item(
    val id: Int,
    val listId: Int,
    val name: String? = null
) {
    override fun toString(): String {
        return "Id : $id, ListId : $listId, Name : $name"
    }
}
